package com.wir.kbe_project.calculator;

import com.wir.kbe_project.application.exceptions.LaptopNotFoundException;
import com.wir.kbe_project.application.Laptop;
import com.wir.kbe_project.datastorage.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CalculatorController {

    private final LaptopRepository repository;

    public CalculatorController(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/calculator/{id}")
    public double one(@PathVariable UUID id) {
        Laptop laptop = repository.findById(id).orElseThrow(() -> new LaptopNotFoundException(id));

        return MWSCalculator.calculateMWS(laptop);
    }

}
