package pl.kinga.insurancesystem.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kinga.insurancesystem.model.Agent;
import pl.kinga.insurancesystem.model.Customer;
import pl.kinga.insurancesystem.model.Policy;
import pl.kinga.insurancesystem.repository.AgentRepository;
import pl.kinga.insurancesystem.repository.CustomerRepository;
import pl.kinga.insurancesystem.repository.PolicyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AgentRepository agentRepository;
    private final PolicyRepository policyRepository;

    public CustomerService(CustomerRepository customerRepository, AgentRepository agentRepository, PolicyRepository policyRepository) {
        this.customerRepository = customerRepository;
        this.agentRepository = agentRepository;
        this.policyRepository = policyRepository;
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findCustomerByIdWithFetch(id);
    }

    @Transactional(readOnly = true)
    public Optional<Customer> getCustomerByPesel(String pesel) {
        return customerRepository.findCustomerByPesel(pesel);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer assignAgentToCustomer(Long customerId, Long agentId) {
        Customer customer = customerRepository.findCustomerByIdWithFetch(customerId).orElseThrow(
                () -> new IllegalStateException("Customer with id " + customerId + " does not exist")
        );

        Agent agent = agentRepository.findById(agentId).orElseThrow(
                () -> new IllegalStateException("Agent with id " + agentId + " does not exist")
        );

        if (customer.getAgent() != null && customer.getAgent().getId().equals(agent.getId())) {
            throw new IllegalArgumentException("Agent is already assigned to this customer");
        }

        customer.setAgent(agent);
        agent.getCustomers().add(customer);

        return customerRepository.save(customer);
    }

    @Transactional
    public Customer assignPolicyToCustomer(Long customerId, Long policyId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new IllegalStateException("Customer with id " + customerId + " does not exist")
        );

        Policy policy = policyRepository.findById(policyId).orElseThrow(
                () -> new IllegalStateException("Policy with id " + policyId + " does not exist")
        );

        if (customer.getPolicies().contains(policy)) {
            throw new IllegalArgumentException("Policy already assigned to this customer");
        }

        customer.getPolicies().add(policy);
        policy.setCustomer(customer);

        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> getCustomersByAgentId(Long agentId) {
        return customerRepository.findCustomersByAgentId(agentId);
    }

    @Transactional(readOnly = true)
    public List<Customer> getCustomersByLastName(String fragment) {
        return customerRepository.findCustomerByLastNameContaining(fragment);
    }


}
