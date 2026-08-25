package pl.kinga.insurancesystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kinga.insurancesystem.dto.AgentRequest;
import pl.kinga.insurancesystem.dto.AgentResponse;
import pl.kinga.insurancesystem.dto.DtoMapper;
import pl.kinga.insurancesystem.model.Agent;
import pl.kinga.insurancesystem.service.AgentService;

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
