package org.example.springbootbackend.dto;


import lombok.Builder;

@Builder
public record EquationDTO(String expression, double result) {
}