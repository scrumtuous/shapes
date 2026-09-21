package com.mcnz.store;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/* http://localhost:8080/h2-console/ 
Check application.properties to verify the port

*/
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByName(String name);
}
