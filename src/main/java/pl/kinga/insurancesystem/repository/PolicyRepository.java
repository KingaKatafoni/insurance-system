package pl.kinga.insurancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kinga.insurancesystem.model.Policy;
import pl.kinga.insurancesystem.model.PolicyType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    List<Policy> findByType(PolicyType type);

    List<Policy> findByHolderName(String holderName);

    List<Policy> findByPremiumAmountGreaterThan(BigDecimal amount);

    List<Policy> findByStartDateAfter(LocalDate date);

    Optional<Policy> findByPolicyNumber(String policyNumber);

    long countByType(PolicyType type);

    boolean existsByPolicyNumber(String policyNumber);

    @Query("SELECT p FROM Policy p WHERE p.type = :type AND  p.premiumAmount BETWEEN :min AND :max ORDER BY p.premiumAmount DESC ")
    List<Policy> findByTypeAndAmountRange(@Param("type") PolicyType type,
                                          @Param("min") BigDecimal min,
                                          @Param("max") BigDecimal max);

    @Query("SELECT AVG(p.premiumAmount) FROM Policy p WHERE p.type = :type")
    Double averagePremiumByType(@Param("type") PolicyType type);

    @Query("SELECT p FROM Policy p WHERE LOWER(p.holderName) LIKE LOWER(CONCAT('%', :fragment, '%')) ")
    List<Policy> searchByHolderName(@Param("fragment") String fragment);

    @Query("SELECT DISTINCT p.holderName FROM Policy p")
    List<String> findAllHolderNames();
}
