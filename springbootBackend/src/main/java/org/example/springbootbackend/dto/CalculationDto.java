package org.example.springbootbackend.dto;

public class CalculationDto {
    private String expression;
    private Double result;

    // Constructor, getters, and setters
    public CalculationDto(String expression, String result) {
        this.expression = expression;
        this.result = Double.valueOf(result);
    }

    // Getters and setters
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
