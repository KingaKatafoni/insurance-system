package pl.kinga.insurancesystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kinga.insurancesystem.dto.CustomerRequest;
import pl.kinga.insurancesystem.dto.CustomerResponse;
import pl.kinga.insurancesystem.dto.DtoMapper;
import pl.kinga.insurancesystem.model.Customer;
import pl.kinga.insurancesystem.service.CustomerService;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final DtoMapper mapper;

    public CustomerController(CustomerService customerService, DtoMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @GetMapping("/customers")
    public List<CustomerResponse> getAllCustomers(){
        return customerService.getAllCustomers().stream()
                .map(mapper::toCustomerResponse)
                .toList();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> getCustomerByIdWithPolicies(@PathVariable Long id){
        return customerService.getCustomerById(id)
                .map(mapper::toCustomerResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customers/pesel/{pesel}")
    public ResponseEntity<CustomerResponse> getCustomerByPesel(@PathVariable String pesel){
        return customerService.getCustomerByPesel(pesel)
                .map(mapper::toCustomerResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customers/search")
    public List<CustomerResponse> getCustomerByLastName(@RequestParam String fragment){
        return customerService.getCustomersByLastName(fragment)
                .stream()
                .map(mapper::toCustomerResponse)
                .toList();
    }

    @GetMapping("/customers/by-agent/{agentId}")
    public List<CustomerResponse> getCustomersByAgentId(@PathVariable Long agentId){
        return customerService.getCustomersByAgentId(agentId).stream()
                .map(mapper::toCustomerResponse)
                .toList();
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody CustomerRequest request){
        Customer customer = mapper.toCustomer(request);
        Customer added = customerService.createCustomer(customer);

        return ResponseEntity.status(201).body(mapper.toCustomerResponse(added));
    }

    @PostMapping("/customers/{customerId}/agent/{agentId}")
    public CustomerResponse assignAgentToCustomer(@PathVariable Long customerId, @PathVariable Long agentId){
        Customer customer = customerService.assignAgentToCustomer(customerId, agentId);

        return mapper.toCustomerResponse(customer);
    }

    @PostMapping("/customers/{customerId}/policies/{policyId}")
    public CustomerResponse assignPolicyToCustomer(@PathVariable Long customerId, @PathVariable Long policyId){
        Customer customer = customerService.assignPolicyToCustomer(customerId, policyId);

        return mapper.toCustomerResponse(customer);
    }
}
