package pl.kinga.insurancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kinga.insurancesystem.model.Customer;
import pl.kinga.insurancesystem.model.PolicyType;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByPesel(String pesel);

    List<Customer> findCustomerByLastNameContaining(String fragment);

    boolean existsByPesel(String pesel);


    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.policies WHERE c.agent.id = :agentId ORDER BY c.lastName")
    List<Customer> findCustomersByAgentId(@Param("agentId") Long agentId);

    @Query("SELECT COUNT(c) FROM Customer c WHERE c.agent.id = :agentId")
    long countByCustomersOfAgent(@Param("agentId") long agentId);

    @Query("SELECT DISTINCT c FROM Customer c JOIN c.policies p WHERE p.type = :type")
    List<Customer> searchCustomerByPolicyType(@Param("type")PolicyType policyType);

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.policies WHERE c.id = :customerId")
    Optional<Customer> findCustomerByIdWithFetch(@Param("customerId") Long customerId);

}

