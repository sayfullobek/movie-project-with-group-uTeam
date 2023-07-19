package Movie.Movie.service;

import Movie.Movie.Repository.AuthRepository;
import Movie.Movie.Repository.RoleRepository;
import Movie.Movie.security.JwtTokenProvider;
import ituniversal.videocourseserver.entity.Course;
import ituniversal.videocourseserver.entity.User;
import ituniversal.videocourseserver.exception.ResourceNotFoundException;
import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.payload.RegisterDto;
import ituniversal.videocourseserver.repository.AuthRepository;
import ituniversal.videocourseserver.repository.CourseRepository;
import ituniversal.videocourseserver.repository.RoleRepository;
import ituniversal.videocourseserver.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return authRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("getUser"));
    }

    public UserDetails getUserById(UUID id) {
        return authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
    }

    public ApiResponse<?> register(RegisterDto registerDto) {
        if (authRepository.existsByPhoneNumber(registerDto.getPhoneNumber()))
            return new ApiResponse<>("Bunday telefon raqam avvaldan mavjud", 409, false);
//        Course qozi = courseRepository.findByName("qozi");
        User user = User.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .phoneNumber(registerDto.getPhoneNumber())
                .password(passwordEncoder().encode(registerDto.getPassword()))
                .roles(Collections.singleton(roleRepository.findById(registerDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException(404, "getRole", "id", registerDto.getRoleId()))))
//                .courses(Collections.singleton(qozi))
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .courses(null)
                .build();
        User save = authRepository.save(user);
        String token = jwtTokenProvider.generateToken(save.getId());
        return new ApiResponse<>(token, 200, true, save);
    }

    public ApiResponse<?> changePassword(UUID id, RegisterDto registerDto) {
        User user = authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(404, "getUser", "id", id));
        if (!user.getPassword().equals(registerDto.getPassword()))
            return new ApiResponse<>("Parolingizda xatolik iltimos qayta urinib ko'ring", 409, false);
        user.setPassword(passwordEncoder().encode(registerDto.getNewPassword()));
        authRepository.save(user);
        return new ApiResponse<>("Muvaffaqiyatli almashtirildi", 200, true);
    }

    public ApiResponse<?> buyFreeCourse(UUID id, RegisterDto registerDto) {
        User user = authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(404, "getUser", "id", id));
        Course course = courseRepository.findById(registerDto.getCourseId()).orElseThrow(() -> new ResourceNotFoundException(404, "getCourse", "course_id", registerDto.getCourseId()));
        user.getCourses().add(course);
        authRepository.save(user);
        return new ApiResponse<>("Muvaffaqiyatli Kurs sotib olindi", 200, true);
    }
}
