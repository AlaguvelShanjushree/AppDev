package com.example.demo.controller;

import com.example.demo.model.Interaction;
import com.example.demo.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interactions")
public class InteractionController {
    @Autowired
    private InteractionService interactionService;

    @GetMapping
    public List<Interaction> getAllInteractions() {
        return interactionService.getAllInteractions();
    }

    @GetMapping("/customer/{customerId}")
    public List<Interaction> getInteractionsByCustomerId(@PathVariable Long customerId) {
        return interactionService.getInteractionsByCustomerId(customerId);
    }

    @PostMapping("/customer/{customerId}")
    public Interaction createInteraction(@PathVariable Long customerId, @RequestBody Interaction interaction) {
        return interactionService.createInteraction(customerId, interaction);
    }

    @PutMapping("/{id}")
    public Interaction updateInteraction(@PathVariable Long id, @RequestBody Interaction interaction) {
        return interactionService.updateInteraction(id, interaction);
    }

    @DeleteMapping("/{id}")
    public void deleteInteraction(@PathVariable Long id) {
        interactionService.deleteInteraction(id);
    }
}
