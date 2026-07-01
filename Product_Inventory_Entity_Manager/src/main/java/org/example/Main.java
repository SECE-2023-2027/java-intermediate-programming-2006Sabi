package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Product p = new Product("P101", "Laptop", 1000.0, 15);
        p.applyDiscount(10);
        System.out.println(p.getPrice());
        System.out.println(p.getStockQuantity());
    }
}