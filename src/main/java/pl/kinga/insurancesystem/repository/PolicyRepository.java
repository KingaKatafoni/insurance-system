package pl.kinga.insurancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kinga.insurancesystem.model.Policy;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    List<Policy> findByType(String type);
}
