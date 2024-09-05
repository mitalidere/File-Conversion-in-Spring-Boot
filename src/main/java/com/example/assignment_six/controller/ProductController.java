package com.example.assignment_six.controller;

import com.example.assignment_six.model.Product;
import com.example.assignment_six.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/importFromCSV")
    public List<Product> importFromCSV() {
        return productService.importFromCSV();
    }

    @PostMapping("/exportToCSV")
    public String exportToCSV(@RequestBody Product product) {
        return productService.exportToCSV(product);
    }

    @PostMapping("/CSVToDatabase")
    public String CSVToDatabase() {
        return productService.CSVToDatabase();
    }

    @PostMapping("/CSVToXML")
    public String CSVToXML() {
        return productService.CSVToXML();
    }
}
