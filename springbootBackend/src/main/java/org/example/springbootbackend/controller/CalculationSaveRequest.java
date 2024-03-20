package org.example.springbootbackend.controller;

public class CalculationSaveRequest {
    private Long userId;
    private String expression;
    private Double result;

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getResult() {

        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
