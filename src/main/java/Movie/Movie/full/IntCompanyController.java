package Movie.Movie.full;

import Movie.Movie.pyload.CompanyDto;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface IntCompanyController {
    HttpEntity<?> getCompany();

    HttpEntity<?> getOneCompany(UUID id);

    HttpEntity<?> addCompany(CompanyDto companyDto);

    HttpEntity<?> editCompany(UUID id, CompanyDto companyDto);

    HttpEntity<?> deleteCompany(UUID id);
}
