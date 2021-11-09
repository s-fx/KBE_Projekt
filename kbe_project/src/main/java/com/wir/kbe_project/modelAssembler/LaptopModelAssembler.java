package com.wir.kbe_project.modelAssembler;

import com.wir.kbe_project.controller.LaptopController;
import com.wir.kbe_project.model.Laptop;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @Component = is created when app starts
 *
 */
@Component
public class LaptopModelAssembler implements RepresentationModelAssembler<Laptop, EntityModel<Laptop>> {


    /**
     * converts a non-model object (laptop) into a model-based object (EntityModel<Laptop>)
     * @param laptop
     * @return
     */
    @Override
    public EntityModel<Laptop> toModel(Laptop laptop) {
        return EntityModel.of(laptop, //
                linkTo(methodOn(LaptopController.class).one(laptop.getId())).withSelfRel(),
                linkTo(methodOn(LaptopController.class).all()).withRel("laptops"));
    }
}