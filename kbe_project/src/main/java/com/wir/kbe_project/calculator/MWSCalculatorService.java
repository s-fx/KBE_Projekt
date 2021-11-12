package com.wir.kbe_project.calculator;

import com.wir.kbe_project.application.Laptop;
import org.springframework.stereotype.Service;

@Service("MWSCalculator")
public class MWSCalculatorService {

    public static double calculateMWS(Laptop laptop) {
        return laptop.getPrice() * 0.18;
    }
}
