package com.wir.kbe_project.datastorage;

import com.wir.kbe_project.application.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(LaptopRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Laptop("HP", 999.99)));
            log.info("Preloading " + repository.save(new Laptop("Lenovo", 1299.99)));
            log.info("Preloading " + repository.save(new Laptop("MacBook Pro", 2499.99)));
            log.info("Preloading " + repository.save(new Laptop("Dell XPS", 799.99)));
            log.info("Preloading " + repository.save(new Laptop("ThinkPad T490", 1099.99)));
            log.info("Preloading " + repository.save(new Laptop("IdeaPad", 699.99)));
            log.info("Preloading " + repository.save(new Laptop("Alienware", 1899.99)));
            log.info("Preloading " + repository.save(new Laptop("Dell N1", 299.99)));
            log.info("Preloading " + repository.save(new Laptop("ThinkPad T590", 2299.99)));
            log.info("Preloading " + repository.save(new Laptop("Lenovo X1", 2999.99)));
        };
    }
}
