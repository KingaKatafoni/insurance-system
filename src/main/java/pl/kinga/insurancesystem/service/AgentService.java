package pl.kinga.insurancesystem.service;

import org.springframework.stereotype.Service;
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

    public List<Agent> getAllAgents(){
        return agentRepository.findAll();
    }

    public Optional<Agent> getAgentById(Long id){
        return agentRepository.findById(id);
    }

    public Agent createAgent(Agent agent){
        return agentRepository.save(agent);
    }

    public Agent assignPolicyToAgent(Long agentId, Long policyId){
        Agent agent = agentRepository.findById(agentId)
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

    public Optional<Agent> getAgentByEmail(String email){
        return agentRepository.findByEmail(email);
    }

    public List<Agent> getAgentByLastName(String lastName){
        return agentRepository.findByLastName(lastName);
    }

    public List<Agent> getAgentByLastNameContaining(String fragment){
        return agentRepository.findByLastNameContaining(fragment);
    }

    public boolean existsAgentByEmail(String email){
        return agentRepository.existsByEmail(email);
    }

    public List<Agent> getAgentsByPolicyType(PolicyType type){
        return agentRepository.findAgentsByPolicyType(type);
    }

    public List<Agent> getAgentsSortedByPolicyCount(){
        return agentRepository.findAgentsSortedByPolicyCount();
    }
}
