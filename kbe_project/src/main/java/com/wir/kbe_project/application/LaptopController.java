package com.wir.kbe_project.application;


import com.wir.kbe_project.application.exceptions.LaptopNotFoundException;
import com.wir.kbe_project.datastorage.LaptopModelAssembler;
import com.wir.kbe_project.datastorage.LaptopRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class LaptopController {

    /**
     * Hier kommt der Aufruf von der separaten Klasse Calculator Service (muss noch erstellt werden)
     * also bspw. wenn wir die Seite localhost:8080/mehrwertsteuer/{id} aufrufen
     * LaptopController übergibt dem Calculator Service den Laptop
     * Laptop wird der Calculator Service Klasse übergeben
     * in der Calculator Service Klasse wird dann wieder eine REST API angesprochen und Laptop mit bestimmten id (CalculatorController) übergeben
     * die CalculatorController REST API gibt Laptop der Calculator Klasse, diese berechnet Merhwertsteuer und gibt es dem CalulcatorController
     * zurück und der Controller schickt das an User zurück
     *
     */

    private final LaptopRepository repository;
    private final LaptopModelAssembler assembler;


    public LaptopController(LaptopRepository repository, LaptopModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    /**
     * CollectionModel = HATEOAS container, encapsulate collection of laptop resources
     * why all these links? => makes it possible to evolve REST services over time
     * @return CollectionModel of laptops
     */
    @GetMapping("/laptops")
    public CollectionModel<EntityModel<Laptop>> all() {
        List<EntityModel<Laptop>> laptops = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(laptops, linkTo(methodOn(LaptopController.class).all()).withSelfRel());
    }

    /**
     * curl -v -X POST localhost:8080/laptops -H 'Content-Type:application/json' -d '{"brand": "HP", "price": 229.99}'
     * @param newLaptop stored in db
     * @return 201 HTTP Created response
     */
    @PostMapping("/laptops")
    ResponseEntity<?> newLaptop(@RequestBody Laptop newLaptop) {
        EntityModel<Laptop> entityModel = assembler.toModel(newLaptop);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Get a single Laptop by id
     * EntityModel<T> = HATEOAS container, containing data and collection of links
     * curl command: curl -v localhost:8080/laptops/1 | json_pp
     * Link: includes a URI and a relation (see assembler)
     * @param id of laptop
     * @return model of laptop through assembler
     */
    @GetMapping("/laptops/{id}")
    public EntityModel<Laptop> one(@PathVariable Long id) {
        Laptop laptop = repository.findById(id).orElseThrow(() -> new LaptopNotFoundException(id));
        return assembler.toModel(laptop);
    }

    /**
     * curl -v -X PUT localhost:8080/laptops/3 -H 'Content-Type:application/json' -d '{"brand": "Lenovo", "price": "499.99"}'
     * @param newLaptop
     * @param id
     * @return
     */
    @PutMapping("/laptops/{id}")
    ResponseEntity<?> replaceLaptop(@RequestBody Laptop newLaptop, @PathVariable Long id) {
        Laptop updatedLaptop = repository.findById(id) //
                .map(laptop -> {
                    laptop.setBrand(newLaptop.getBrand());
                    laptop.setPrice(newLaptop.getPrice());
                    return repository.save(laptop);
                }) //
                .orElseGet(() -> {
                    newLaptop.setId(id);
                    return repository.save(newLaptop);
                });

        EntityModel<Laptop> entityModel = assembler.toModel(updatedLaptop);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    /**
     * curl -v -X DELETE localhost:8080/laptops/1
     * @param id
     * @return
     */
    @DeleteMapping("/laptops/{id}")
    ResponseEntity<?> deleteLaptop(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
