package pl.kinga.insurancesystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kinga.insurancesystem.model.Agent;
import pl.kinga.insurancesystem.model.Policy;
import pl.kinga.insurancesystem.model.PolicyType;
import pl.kinga.insurancesystem.repository.AgentRepository;
import pl.kinga.insurancesystem.repository.PolicyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    private final AgentRepository agentRepository;
    private final PolicyRepository policyRepository;

    public AgentService(AgentRepository agentRepository, PolicyRepository policyRepository){
        this.agentRepository = agentRepository;
        this.policyRepository = policyRepository;
    }

    @Transactional(readOnly = true)
    public List<Agent> getAllAgents(){
        return agentRepository.findAllWithPoliciesGraph();
    }


    @Transactional(readOnly = true)
    public Optional<Agent> getAgentById(Long id) {
        return agentRepository.findByIdWithPolicies(id);
    }

    public Agent createAgent(Agent agent){
        return agentRepository.save(agent);
    }

    public Agent assignPolicyToAgent(Long agentId, Long policyId){
        Agent agent = agentRepository.findByIdWithPolicies(agentId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Agent with id " + agentId + " does not exist")
                );
        Policy policy = policyRepository.findById(policyId).orElseThrow(
                () -> new IllegalArgumentException("Policy with id " + policyId + " does not exist")
        );

        if (agent.getPolicies().contains(policy)) {
            throw new IllegalArgumentException("Policy already assigned to this agent");
        }
        agent.getPolicies().add(policy);
        return agentRepository.save(agent);
    }

    @Transactional(readOnly = true)
    public Optional<Agent> getAgentByEmail(String email){
        return agentRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Agent> getAgentByLastName(String lastName){
        return agentRepository.findByLastName(lastName);
    }

    @Transactional(readOnly = true)
    public List<Agent> getAgentByLastNameContaining(String fragment){
        return agentRepository.searchByLastNameWithPolicies(fragment);
    }

    @Transactional(readOnly = true)
    public boolean existsAgentByEmail(String email){
        return agentRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Agent> getAgentsByPolicyType(PolicyType type){
        return agentRepository.findAgentsByPolicyType(type);
    }

    @Transactional(readOnly = true)
    public List<Agent> getAgentsSortedByPolicyCount(){
        return agentRepository.findAgentsSortedByPolicyCount();
    }


    @Transactional
    public void transferPolicy(Long fromAgentId, Long toAgentId, Long policyId){
        Agent fromAgent = agentRepository.findByIdWithPolicies(fromAgentId).orElseThrow(
                () -> new IllegalArgumentException("Not found Agent with id " + fromAgentId)
        );

        Policy policy = policyRepository.findById(policyId).orElseThrow(
                () -> new IllegalArgumentException("Not found Policy with id " + policyId)
        );

        if (!fromAgent.getPolicies().contains(policy)){
            throw new IllegalStateException("Policy " + policyId + " is not assign to Agent " + fromAgentId);
        }

        fromAgent.getPolicies().remove(policy);
        agentRepository.save(fromAgent);

        Agent toAgent = agentRepository.findByIdWithPolicies(toAgentId).orElseThrow(
                () -> new IllegalArgumentException("Not found Agent with id " + toAgentId)
        );

        toAgent.getPolicies().add(policy);
        agentRepository.save(toAgent);
    }



}
