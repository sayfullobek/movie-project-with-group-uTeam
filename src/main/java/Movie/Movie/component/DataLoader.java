package Movie.Movie.component;

import Movie.Movie.Entity.Enums.RoleName;
import Movie.Movie.Entity.Role;
import Movie.Movie.Entity.User;
import Movie.Movie.Repository.AuthRepository;
import Movie.Movie.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (init.equals("create-drop") || init.equals("create")) {
//            for (RoleName value : RoleName.values()) {
//                roleRepository.save(new Role(value));
//            }
//            User admin = authRepository.save(
//                    new User(
//                            "Shavkat", "Nazarov", "904410801", passwordEncoder.encode("shavkat0801"), Collections.singleton(roleRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("getRole"))), true, true, true, true
//                    )
//            );
//            User teacher = authRepository.save(
//                    new User(
//                            "Dilbek", "Mukhtarovich", "500537027", passwordEncoder.encode("dilbekk070"), Collections.singleton(roleRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("getRole"))), true, true, true, true
//                    )
//            );
        }
    }
}
