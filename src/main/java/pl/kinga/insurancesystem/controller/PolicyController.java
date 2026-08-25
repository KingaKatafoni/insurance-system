package pl.kinga.insurancesystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kinga.insurancesystem.dto.DtoMapper;
import pl.kinga.insurancesystem.dto.PolicyRequest;
import pl.kinga.insurancesystem.dto.PolicyResponse;
import pl.kinga.insurancesystem.model.Policy;
import pl.kinga.insurancesystem.model.PolicyType;
import pl.kinga.insurancesystem.service.PolicyService;

import java.util.List;

@RestController
public class PolicyController {

    private final PolicyService service;
    private final DtoMapper mapper;

    public PolicyController(PolicyService service, DtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;

    }

    @GetMapping("/policies")
    public List<PolicyResponse> getAllPolicies() {
        return service.getAllPolicies().stream()
                .map(mapper::toPolicyResponse)
                .toList();
    }

    @GetMapping("/policies/{id}")
    public ResponseEntity<PolicyResponse> getPolicyById(@PathVariable Long id) {
        return service.getPolicyById(id)
                .map(mapper::toPolicyResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/policies/type/{type}")
    public List<PolicyResponse> getPolicyByType(@PathVariable PolicyType type) {
        return service.getPoliciesByType(type).stream()
                .map(mapper::toPolicyResponse)
                .toList();
    }

    @PostMapping("/policies")
    public ResponseEntity<PolicyResponse> addNewPolicy(@Valid @RequestBody PolicyRequest request) {
        Policy policy = mapper.toPolicy(request);
        Policy added = service.createPolicy(policy);
        return ResponseEntity.status(201).body(mapper.toPolicyResponse(added));

    }


}
