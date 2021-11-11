package com.wir.kbe_project.calculator;

import com.wir.kbe_project.controller.LaptopController;
import com.wir.kbe_project.exceptions.LaptopNotFoundException;
import com.wir.kbe_project.model.Laptop;
import com.wir.kbe_project.modelAssembler.LaptopModelAssembler;
import com.wir.kbe_project.repository.LaptopRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.wir.kbe_project.repository.LaptopRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final LaptopRepository repository;

    public CalculatorController(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/calculator/{id}")
    public double one(@PathVariable Long id) {
        Laptop laptop = repository.findById(id).orElseThrow(() -> new LaptopNotFoundException(id));

        return MWSCalculator.calculateMWS(laptop);
    }

}
