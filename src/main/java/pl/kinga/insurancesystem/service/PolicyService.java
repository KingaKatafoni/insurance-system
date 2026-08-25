package pl.kinga.insurancesystem.service;

import org.springframework.stereotype.Service;
import pl.kinga.insurancesystem.model.Policy;
import pl.kinga.insurancesystem.model.PolicyType;
import pl.kinga.insurancesystem.repository.PolicyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {
    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Optional<Policy> getPolicyById(Long id) {
        return policyRepository.findById(id);
    }

    public List<Policy> getPoliciesByType(PolicyType type) {
        return policyRepository.findByType(type);
    }

    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

}
