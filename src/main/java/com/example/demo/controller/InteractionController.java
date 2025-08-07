package com.example.demo.controller;

import com.example.demo.model.Interaction;
import com.example.demo.service.InteractionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InteractionController {

    @Autowired
    private InteractionService interactionService;

    @PostMapping("/interactions")
    public ResponseEntity<?> createInteraction(@Valid @RequestBody Interaction interaction) {
        try {
            Long customerId = interaction.getCustomer().getId();
            Interaction saved = interactionService.createInteraction(customerId, interaction);
            return ResponseEntity.status(201).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

        // Get all interactions
    @GetMapping("/interactions")
    public List<Interaction> getAllInteractions() {
        return interactionService.getAllInteractions();
}


    @GetMapping("/customers/{customerId}/interactions")
    public ResponseEntity<?> getInteractionsByCustomer(@PathVariable Long customerId) {
        try {
            List<Interaction> interactions = interactionService.getInteractionsByCustomerId(customerId);
            return ResponseEntity.ok(interactions);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
