package Movie.Movie.full;

import Movie.Movie.pyload.ApiResponse;
import Movie.Movie.pyload.CompanyDto;

import java.util.UUID;

public interface IntCompanyService {
    ApiResponse addCompany(CompanyDto companyDto);

    ApiResponse editCompany(UUID id, CompanyDto companyDto);

    ApiResponse deleteCompany(UUID id);
}
