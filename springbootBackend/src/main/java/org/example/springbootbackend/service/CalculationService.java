package org.example.springbootbackend.service;

import org.example.springbootbackend.dto.ExpressionDTO;
import org.example.springbootbackend.dto.EquationDTO;
import org.example.springbootbackend.dto.UserDTO;
import org.example.springbootbackend.exception.UserNotFoundException;
import org.example.springbootbackend.mapper.CalculationMapper;
import org.example.springbootbackend.model.Calculation;
import org.example.springbootbackend.model.Equation;
import org.example.springbootbackend.model.User;
import org.example.springbootbackend.repo.CalculationRepository;
import org.example.springbootbackend.repo.EquationRepository;
import org.example.springbootbackend.repo.UserRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CalculationService {


    @Autowired
    private CalculationRepository calculationRepository;

    private final EquationRepository equationRepository;
    private final CalculationMapper calculationMapper;
    private final Logger logger = LoggerFactory.getLogger(CalculationService.class);
    private final UserRepository userRepository;


    public CalculationService(EquationRepository equationRepository, CalculationMapper calculationMapper,
                              UserRepository userRepository) {
        this.equationRepository = equationRepository;
        this.calculationMapper = calculationMapper;
        this.userRepository = userRepository;
    }

    public double calculate(ExpressionDTO expression) {
        double baseVal = Double.parseDouble(expression.elements().remove(0));
        while(!expression.elements().isEmpty()) {
            baseVal = switch (expression.elements().remove(0)) {
                case "x" -> {
                    logger.info("Multiplication operation was used");
                    yield baseVal * Double.parseDouble(expression.elements().remove(0));
                }
                case "/" -> {
                    logger.info("Division operation was used");
                    yield baseVal / Double.parseDouble(expression.elements().remove(0));
                }
                case "+" -> {
                    logger.info("Addition operation was used");
                    yield baseVal + Double.parseDouble(expression.elements().remove(0));
                }
                case "-" -> {
                    logger.info("Subtraction operation was used");
                    yield baseVal - Double.parseDouble(expression.elements().remove(0));
                }
                default -> throw new IllegalArgumentException("The operator is invalid");
            };
        }
        return baseVal;
    }

    public double saveExpression(ExpressionDTO expression, String name) {
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(() -> new UserNotFoundException(name));
        double result = calculate(new ExpressionDTO(new ArrayList<>(expression.elements())));
        Equation equation = equationRepository.save(calculationMapper.toEquation(expression, result, user));
        System.out.println(equation);
        logger.info("Expression saved");
        return result;
    }

    public List<EquationDTO> getAllEquations(UserDTO user) {
        return equationRepository.findAllByUser_Username(user.getUsername())
                .orElseThrow(() -> new UserNotFoundException(user.getUsername()))
                .stream().map(CalculationMapper::toEquationDTO).toList();
    }

    public List<ExpressionDTO> getAllExpressions() {
        List<Equation> equations = equationRepository.findAll();
        return  transformEquationListToExpression(equations);
    }

    private List<ExpressionDTO> transformEquationListToExpression(List<Equation> equations) {
        return equations
                .stream()
                .map(calculationMapper::toExpression)
                .toList();
    }




    public void saveCalculation(Long userId, String expression, String result) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Calculation calculation = new Calculation();
        calculation.setExpression(expression);
        calculation.setResult(String.valueOf(Double.parseDouble(result)));
        calculation.setUser(user);

        calculationRepository.save(calculation);
    }




    // In CalculationService.java
    public List<Calculation> getCalculationHistoryForUser(Long userId) {
        // This is assuming you have a method in your CalculationRepository to find calculations by userId
        return calculationRepository.findByUserId(userId);
    }


}