package com.example.angular.Springbootcruddemo.controller;


import com.example.angular.Springbootcruddemo.ResourceNotFoundException;
import com.example.angular.Springbootcruddemo.model.Flow;
import com.example.angular.Springbootcruddemo.repository.FlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/api")

public class FlowController {

    @Autowired
    private FlowRepository flowRepository;

    @PostMapping("/flows")
    public Flow addFlow(@RequestBody Flow flow) {
        return flowRepository.save(flow);
    }

    @GetMapping("/flows")
    public ResponseEntity<List<Flow>> getAllFlows() {
        return ResponseEntity.ok(flowRepository.findAll());
    }

    @GetMapping("/flows/{id}")
    public ResponseEntity<Flow> getFlowById(@PathVariable(value = "id") Integer flowId)
            throws ResourceNotFoundException {
        Flow flow = flowRepository.findById(flowId)
                .orElseThrow(() -> new ResourceNotFoundException("Flow not found for this id : " + flowId));
        return ResponseEntity.ok().body(flow);
    }

    @PutMapping("/flows/{id}")
    public ResponseEntity<Flow> updateFlow(@PathVariable(value = "id") Integer flowId,
            @RequestBody Flow flowDetails) throws ResourceNotFoundException {

        Flow flow = flowRepository.findById(flowId)
                .orElseThrow(() -> new ResourceNotFoundException("Flow not found for this id : " + flowId));

        flow.setName(flowDetails.getName());
        flow.setPhone(flowDetails.getPhone());

        final Flow updatedFlow = flowRepository.save(flow);
        return ResponseEntity.ok(updatedFlow);
    }

    @DeleteMapping("/flows/{id}")
    public Map<String,Boolean> deleteFlow(@PathVariable(value = "id") Integer flowId) throws ResourceNotFoundException{

        Flow flow = flowRepository.findById(flowId)
                .orElseThrow(()-> new ResourceNotFoundException("Flow not found for this id : " + flowId));

        flowRepository.delete(flow);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

}
