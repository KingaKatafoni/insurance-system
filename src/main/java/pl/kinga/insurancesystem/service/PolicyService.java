package pl.kinga.insurancesystem.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import pl.kinga.insurancesystem.model.Policy;
import pl.kinga.insurancesystem.model.PolicyType;
import pl.kinga.insurancesystem.repository.PolicyRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {
    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }


    @Transactional(readOnly = true)
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Policy> getPolicyById(Long id) {
        return policyRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Policy> getPoliciesByType(PolicyType type) {
        return policyRepository.findByType(type);
    }

    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    @Transactional(readOnly = true)
    public List<Policy> getPoliciesByHolder(String holderName){
        return policyRepository.findByHolderName(holderName);
    }

    @Transactional(readOnly = true)
    public List<Policy> getExpensivePolicies(BigDecimal minAmount){
        return policyRepository.findByPremiumAmountGreaterThan(minAmount);
    }

    @Transactional(readOnly = true)
    public List<Policy> getPoliciesStartingAfter(LocalDate date){
        return policyRepository.findByStartDateAfter(date);
    }

    @Transactional(readOnly = true)
    public Optional<Policy> getPolicyByNumber(String policyNumber){
        return policyRepository.findByPolicyNumber(policyNumber);
    }

    @Transactional(readOnly = true)
    public long countPoliciesByType(PolicyType type){
        return policyRepository.countByType(type);
    }

    @Transactional(readOnly = true)
    public List<Policy> getPoliciesByTypeAndAmountRange(PolicyType type, BigDecimal min, BigDecimal max){
        return policyRepository.findByTypeAndAmountRange(type, min, max);
    }

    @Transactional(readOnly = true)
    public Double getAveragePremiumByType(PolicyType type){
        return policyRepository.averagePremiumByType(type);
    }

    @Transactional(readOnly = true)
    public List<Policy> searchPoliciesByHolderName(String fragment){
        return policyRepository.searchByHolderName(fragment);
    }

    @Transactional(readOnly = true)
    public List<String> getAllHolderNames(){
        return policyRepository.findAllHolderNames();
    }

}
