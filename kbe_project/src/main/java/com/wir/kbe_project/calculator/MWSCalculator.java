package com.wir.kbe_project.calculator;

import com.wir.kbe_project.application.Laptop;

public class MWSCalculator {

    public static double calculateMWS(Laptop laptop) {
        return laptop.getPrice() * 0.18;
    }
}
