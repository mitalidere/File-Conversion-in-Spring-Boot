package com.example.assignment_six.controller;

import com.example.assignment_six.model.Product;
import com.example.assignment_six.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/importFromCSV")
    public List<Product> importFromCSV(@RequestParam String CSVPath) {
        return productService.importFromCSV(CSVPath);
    }

    @PostMapping("/exportToCSV")
    public String exportToCSV(@RequestBody Product product, @RequestParam String CSVPath) {
        return productService.exportToCSV(product, CSVPath);
    }

    @PostMapping("/CSVToDatabase")
    public String CSVToDatabase(@RequestParam String CSVPath) {
        return productService.CSVToDatabase(CSVPath);
    }

    @PostMapping("/databaseToCSV")
    public String DatabaseToCSV(@RequestParam String CSVPath) {
        return productService.DatabaseToCSV(CSVPath);
    }

    @PostMapping("/CSVToXML")
    public String CSVToXML(@RequestParam String CSVPath, @RequestParam String XMLPath) {
        return productService.CSVToXML(CSVPath, XMLPath);
    }
}
