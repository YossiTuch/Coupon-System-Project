package app.core.repositories;

import java.util.Optional;

import app.core.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByIdIsNotAndEmail(long customerId, String email);
}
