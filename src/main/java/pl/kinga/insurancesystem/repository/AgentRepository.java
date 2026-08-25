package pl.kinga.insurancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kinga.insurancesystem.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {

}
