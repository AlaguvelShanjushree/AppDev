package com.example.demo.repository;

import com.example.demo.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    List<Interaction> findByCustomerId(Long customerId);
}
