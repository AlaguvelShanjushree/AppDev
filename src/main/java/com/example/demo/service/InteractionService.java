package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Interaction;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InteractionService {
    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Interaction> getAllInteractions() {
        return interactionRepository.findAll();
    }

    public List<Interaction> getInteractionsByCustomerId(Long customerId) {
        return interactionRepository.findByCustomerId(customerId);
    }

    public Interaction createInteraction(Long customerId, Interaction interaction) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            interaction.setCustomer(customer);
            interaction.setTimestamp(LocalDateTime.now());
            return interactionRepository.save(interaction);
        }
        return null;
    }

    public Interaction updateInteraction(Long id, Interaction updatedInteraction) {
        Interaction interaction = interactionRepository.findById(id).orElse(null);
        if (interaction != null) {
            interaction.setType(updatedInteraction.getType());
            interaction.setNotes(updatedInteraction.getNotes());
            return interactionRepository.save(interaction);
        }
        return null;
    }

    public void deleteInteraction(Long id) {
        interactionRepository.deleteById(id);
    }
}
