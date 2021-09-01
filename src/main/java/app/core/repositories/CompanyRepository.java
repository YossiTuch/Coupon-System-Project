package app.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByEmail(String email);

	//check if works, if not change to query!
	Optional<Company> findByEmailOrName(String email, String name);
	Optional<Company> findByIdIsNotAndEmail(long companyId, String email);
}
