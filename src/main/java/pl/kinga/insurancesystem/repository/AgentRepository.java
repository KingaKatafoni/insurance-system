package pl.kinga.insurancesystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kinga.insurancesystem.model.Agent;
import pl.kinga.insurancesystem.model.PolicyType;

import java.util.List;
import java.util.Optional;


public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findByEmail(String email);

    List<Agent> findByLastName(String lastName);

    List<Agent> findByLastNameContaining(String fragment);

    boolean existsByEmail(String email);

    @Query("SELECT DISTINCT a FROM Agent a JOIN a.policies p WHERE p.type = :type")
    List<Agent> findAgentsByPolicyType(@Param("type")PolicyType type);

    @Query("SELECT a FROM Agent a LEFT JOIN a.policies p GROUP BY a ORDER BY COUNT(p) DESC")
    List<Agent> findAgentsSortedByPolicyCount();

    @Query("SELECT a FROM Agent a LEFT JOIN FETCH a.policies WHERE a.id = :id")
    Optional<Agent> findByIdWithPolicies(@Param("id") Long id);

    @Query("SELECT DISTINCT a FROM Agent a LEFT JOIN FETCH a.policies")
    List<Agent> findAllWithPolicies();

    @EntityGraph(attributePaths = {"policies"})
    @Query("SELECT a FROM Agent a")
    List<Agent> findAllWithPoliciesGraph();

    @EntityGraph(attributePaths = {"policies"})
    @Query("SELECT a FROM Agent a WHERE a.lastName LIKE %:fragment%")
    List<Agent> searchByLastNameWithPolicies(@Param("fragment") String fragment);



}
