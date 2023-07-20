package Movie.Movie.service;

import Movie.Movie.Entity.Company;
import Movie.Movie.Repository.CompanyRepository;
import Movie.Movie.full.IntCompanyService;
import Movie.Movie.pyload.ApiResponse;
import Movie.Movie.pyload.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService implements IntCompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public ApiResponse addCompany(CompanyDto companyDto) {
        try {
            Company company = Company.builder()
                    .username(companyDto.getUsername())
                    .password(companyDto.getPassword())
                    .build();
            companyRepository.save(company);
            return new ApiResponse("Successfully saved", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik", false);
        }
    }

    @Override
    public ApiResponse editCompany(UUID id, CompanyDto companyDto) {
        try {
            Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getCompany"));
            company.setUsername(companyDto.getUsername());
            company.setPassword(companyDto.getPassword());
            companyRepository.save(company);
            return new ApiResponse("successfully edited", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik", false);
        }
    }
    @Override
    public ApiResponse deleteCompany(UUID id) {
        try{
            Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getCompany"));
                companyRepository.delete(company);
            return new ApiResponse("successfully deleted", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik", false);
        }
    }
}
