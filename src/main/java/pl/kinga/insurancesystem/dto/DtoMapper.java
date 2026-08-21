package pl.kinga.insurancesystem.dto;

import org.springframework.stereotype.Component;
import pl.kinga.insurancesystem.model.Policy;

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

}
