package pl.kinga.insurancesystem.dto;

import org.springframework.stereotype.Component;
import pl.kinga.insurancesystem.model.Agent;
import pl.kinga.insurancesystem.model.Policy;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoMapper {

    public PolicyResponse toPolicyResponse(Policy policy) {
        return new PolicyResponse(
                policy.getId(),
                policy.getPolicyNumber(),
                policy.getType(),
                policy.getHolderName(),
                policy.getPremiumAmount(),
                policy.getStartDate(),
                policy.getEndDate()
        );
    }

    public Policy toPolicy(PolicyRequest request) {
        return new Policy(
                request.policyNumber(),
                request.type(),
                request.holderName(),
                request.premiumAmount(),
                request.startDate(),
                request.endDate()
        );
    }

    public AgentResponse toAgentResponse(Agent agent) {
        List<String> policyNumbers = agent.getPolicies().stream()
                .map(Policy::getPolicyNumber)
                .toList();

        String fullName = agent.getFirstName() + " " + agent.getLastName();

        return new AgentResponse(
                agent.getId(),
                fullName,
                agent.getEmail(),
                policyNumbers,
                agent.getPhoneNumber()
        );

    }

    public Agent toAgent(AgentRequest request) {
        return new Agent(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.phoneNumber()
        );
    }


}
