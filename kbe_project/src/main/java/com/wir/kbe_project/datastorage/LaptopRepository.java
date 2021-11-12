package com.wir.kbe_project.datastorage;

import com.wir.kbe_project.application.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
