package com.wir.kbe_project.controller;


import com.wir.kbe_project.exceptions.LaptopNotFoundException;
import com.wir.kbe_project.model.Laptop;
import com.wir.kbe_project.repository.LaptopRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaptopController {

    private final LaptopRepository repository;


    public LaptopController(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/laptops")
    List<Laptop> all(){
        return repository.findAll();
    }

    @PostMapping("/laptops")
    Laptop newLaptop(@RequestBody Laptop newLaptop) {
        return repository.save(newLaptop);
    }

    @GetMapping("/laptops/{id}")
    Laptop one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new LaptopNotFoundException(id));
    }

    @PutMapping("/laptops/{id}")
    Laptop replaceLaptop(@RequestBody Laptop newLaptop, @PathVariable Long id) {

        return repository.findById(id)
                .map(laptop -> {
                    laptop.setBrand(newLaptop.getBrand());
                    laptop.setPrice(newLaptop.getPrice());
                    return repository.save(laptop);
                })
                .orElseGet(() -> {
                    newLaptop.setId(id);
                    return repository.save(newLaptop);
                });
    }

    @DeleteMapping("/laptops/{id}")
    void deleteLaptop(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
