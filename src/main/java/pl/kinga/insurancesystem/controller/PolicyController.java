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

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @GetMapping("/policies/holder/{name}")
    public List<PolicyResponse> getPolicyByHolder(@PathVariable String name) {
        return service.getPoliciesByHolder(name)
                .stream().map(mapper::toPolicyResponse)
                .toList();
    }

    @GetMapping("/policies/expensive")
    public List<PolicyResponse> getExpensive(@RequestParam BigDecimal min) {
        return service.getExpensivePolicies(min).stream()
                .map(mapper::toPolicyResponse)
                .toList();
    }

    @GetMapping("/policies/after")
    public List<PolicyResponse> getPoliciesStartingAfter(@RequestParam LocalDate date) {
        return service.getPoliciesStartingAfter(date).stream()
                .map(mapper::toPolicyResponse)
                .toList();
    }

    @GetMapping("/policies/number/{number}")
    public ResponseEntity<PolicyResponse> getPolicyByNumber(@PathVariable String number){
        return service.getPolicyByNumber(number)
                .map(mapper::toPolicyResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/policies/count/{type}")
    public long getPolicyCountByType(@PathVariable PolicyType type){
        return service.countPoliciesByType(type);
    }

    @GetMapping("/policies/filter")
    public List<PolicyResponse> getPoliciesByTypeAndRange(@RequestParam PolicyType type,
                                                          @RequestParam BigDecimal min,
                                                          @RequestParam BigDecimal max){
        return service.getPoliciesByTypeAndAmountRange(type, min, max)
                .stream()
                .map(mapper::toPolicyResponse)
                .toList();
    }

    @GetMapping("/policies/average/{type}")
    public Double getAveragePremiumByType(@PathVariable PolicyType type){
        return service.getAveragePremiumByType(type);
    }

    @GetMapping("/policies/search")
    public List<PolicyResponse> getHolderByLastName(@RequestParam String fragment){
        return service.searchPoliciesByHolderName(fragment).stream()
                .map(mapper::toPolicyResponse)
                .toList();
    }

    @GetMapping("/policies/holders")
    public List<String> getAllHolders(){
        return service.getAllHolderNames();
    }


    @PostMapping("/policies")
    public ResponseEntity<PolicyResponse> addNewPolicy(@Valid @RequestBody PolicyRequest request) {
        Policy policy = mapper.toPolicy(request);
        Policy added = service.createPolicy(policy);
        return ResponseEntity.status(201).body(mapper.toPolicyResponse(added));

    }


}
