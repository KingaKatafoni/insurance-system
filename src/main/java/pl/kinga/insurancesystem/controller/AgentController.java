package pl.kinga.insurancesystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kinga.insurancesystem.dto.AgentRequest;
import pl.kinga.insurancesystem.dto.AgentResponse;
import pl.kinga.insurancesystem.dto.DtoMapper;
import pl.kinga.insurancesystem.model.Agent;
import pl.kinga.insurancesystem.model.PolicyType;
import pl.kinga.insurancesystem.service.AgentService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AgentController {
    private final AgentService agentService;
    private final DtoMapper mapper;

    public AgentController(AgentService agentService, DtoMapper mapper){
        this.agentService = agentService;
        this.mapper = mapper;
    }

    @GetMapping("/agents")
    public List<AgentResponse> getAllAgents(){
        return agentService.getAllAgents().stream()
                .map(mapper::toAgentResponse)
                .toList();
    }

    @GetMapping("/agents/{id}")
    public ResponseEntity<AgentResponse> getAgentById(@PathVariable Long id){
        return agentService.getAgentById(id)
                .map(mapper::toAgentResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/agents/email/{email}")
    public ResponseEntity<AgentResponse> getAgentByEmail(@PathVariable String email){
        return agentService.getAgentByEmail(email)
                .map(mapper::toAgentResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/agents/search")
    public List<AgentResponse> getAgentByLastNameAndFragment(
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String fragment){

        if(lastName != null){
            return agentService.getAgentByLastName(lastName).stream()
                    .map(mapper::toAgentResponse)
                    .toList();
        } else if (fragment != null){
            return agentService.getAgentByLastNameContaining(fragment).stream()
                    .map(mapper::toAgentResponse)
                    .toList();
        }
        return new ArrayList<>();
    }

    @GetMapping("/agents/by-policy-type/{type}")
    public List<AgentResponse> getAgentsByType(@PathVariable PolicyType type){
        return agentService.getAgentsByPolicyType(type).stream()
                .map(mapper::toAgentResponse)
                .toList();
    }

    @GetMapping("/agents/top")
    public List<AgentResponse> getSortedAgents(){
        return agentService.getAgentsSortedByPolicyCount().stream()
                .map(mapper::toAgentResponse)
                .toList();
    }

    @PostMapping("/agents")
    public ResponseEntity<AgentResponse> addNewAgent(@Valid @RequestBody AgentRequest request){
        Agent agent = mapper.toAgent(request);
        Agent added = agentService.createAgent(agent);
        return ResponseEntity.status(201).body(mapper.toAgentResponse(added));
    }

    @PostMapping("/agents/{agentId}/policies/{policyId}")
    public AgentResponse assignPolicyToAgent(@PathVariable Long agentId, @PathVariable Long policyId){
        Agent agent = agentService.assignPolicyToAgent(agentId, policyId);
        return mapper.toAgentResponse(agent);
    }
}
