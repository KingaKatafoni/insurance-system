package pl.kinga.insurancesystem.dto;

import org.springframework.stereotype.Component;
import pl.kinga.insurancesystem.model.Agent;
import pl.kinga.insurancesystem.model.Customer;
import pl.kinga.insurancesystem.model.Policy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public CustomerResponse toCustomerResponse(Customer customer){
        String fullName = customer.getFirstName() + " " + customer.getLastName();
        String agentName = null;
        if(customer.getAgent() != null) {
            agentName = customer.getAgent().getFirstName() + " " + customer.getAgent().getLastName();
        }


        List<String> policyNumbers = customer.getPolicies().stream()
                .map(Policy::getPolicyNumber)
                .toList();



        return new CustomerResponse(
                customer.getId(),
                fullName,
                customer.getPesel(),
                customer.getEmail(),
                customer.getBirthDate(),
                agentName,
                policyNumbers

        );
    }

    public Customer toCustomer(CustomerRequest request){
        return new Customer(
                request.firstName(),
                request.lastName(),
                request.pesel(),
                request.email(),
                request.birthDate()
        );
    }


}
