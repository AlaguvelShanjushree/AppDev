package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionService {

    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Interaction createInteraction(Long customerId, Interaction interaction) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
        interaction.setCustomer(customer);
        return interactionRepository.save(interaction);
    }

    public List<Interaction> getInteractionsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
        return interactionRepository.findByCustomer(customer);
    }

    public List<Interaction> getAllInteractions() {
    return interactionRepository.findAll();
}

}
