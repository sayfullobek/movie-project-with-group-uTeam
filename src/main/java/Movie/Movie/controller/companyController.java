package Movie.Movie.controller;

import Movie.Movie.Entity.Company;
import Movie.Movie.Repository.CompanyRepository;
import Movie.Movie.full.IntCompanyController;
import Movie.Movie.pyload.ApiResponse;
import Movie.Movie.pyload.CompanyDto;
import Movie.Movie.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class companyController implements IntCompanyController {
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    @GetMapping
    public HttpEntity<?>getCompany(){
        List<Company> all = companyRepository.findAll();
    return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    @Override
    public HttpEntity<?> getOneCompany(@PathVariable UUID id) {
        try {
            Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("geCompany"));
            return ResponseEntity.ok(company);
        } catch (Exception e) {
            return null;
        }
    }
    @PostMapping
    @Override
    public HttpEntity<?> addCompany(@RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    @PutMapping("/{id}")
    public HttpEntity<?> editCompany(@PathVariable UUID id,@RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = companyService.editCompany(id, companyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    @Override
    public HttpEntity<?> deleteCompany( @PathVariable UUID id) {
        ApiResponse apiResponse = companyService.deleteCompany(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
