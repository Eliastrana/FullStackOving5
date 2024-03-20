package org.example.springbootbackend.controller;

import org.example.springbootbackend.dto.CalculationDto;
import org.example.springbootbackend.model.Calculation;
import org.example.springbootbackend.service.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static net.logstash.logback.argument.StructuredArguments.keyValue;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/calculator")
public class CalculationController {

    private final CalculationService calculationService;

    Logger logger = LoggerFactory.getLogger(CalculationController.class);

    private ControllerModel calculatorModel = new ControllerModel();



    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }



    @GetMapping("/evaluate")
    public ResponseEntity<Double> evaluate(@RequestParam String expression) {
        try {
            double result = calculatorModel.evaluate(expression);
            logger.info("Calculation performed", keyValue("calculation", expression), keyValue("result", result));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error evaluating expression", keyValue("expression", expression), keyValue("error", e.getMessage()));
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveCalculation(@RequestBody CalculationSaveRequest request) {
        try {
            logger.info("trying to save calculation", keyValue("user", request.getUserId()), keyValue("calculation", request.getExpression()), keyValue("result", request.getResult()));
            calculationService.saveCalculation(request.getUserId(), request.getExpression(), String.valueOf(request.getResult()));
            logger.info("Calculation saved successfully.", keyValue("user", request.getUserId()), keyValue("calculation", request.getExpression()), keyValue("result", request.getResult()));
            return ResponseEntity.ok().body("Calculation saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save calculation: " + e.getMessage());
        }
    }


    @GetMapping("/calculations/history/{userId}")
    public ResponseEntity<List<CalculationDto>> getCalculationHistoryByUserId(@PathVariable Long userId) {
        try {
            List<Calculation> calculations = calculationService.getCalculationHistoryForUser(userId);
            if (calculations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Map the Calculation entities to Data Transfer Objects (DTOs) to send to the client
            List<CalculationDto> calculationDtos = calculations.stream()
                    .map(calculation -> new CalculationDto(calculation.getExpression(), calculation.getResult()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(calculationDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting calculation history for user: {}", userId, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }











}
