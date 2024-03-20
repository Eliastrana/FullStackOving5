package org.example.springbootbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.example.springbootbackend.model.Calculation;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {

    List<Calculation> findByUserId(Long userId);


}