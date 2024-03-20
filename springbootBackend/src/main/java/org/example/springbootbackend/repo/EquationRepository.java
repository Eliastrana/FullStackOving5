package org.example.springbootbackend.repo;


import org.example.springbootbackend.model.Equation;
import org.example.springbootbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquationRepository extends JpaRepository<Equation, Integer> {
    Optional<List<Equation>> findAllByUser(User user);
    Optional<List<Equation>> findAllByUser_Username(String username);
}