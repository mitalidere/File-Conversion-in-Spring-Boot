package com.example.assignment_six.service;

import com.example.assignment_six.model.Product;
import com.example.assignment_six.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    List<Product> productList = new ArrayList<>();
    File CSVFilePath = new File("C:\\Users\\DELL\\IdeaProjects\\BITS Java Training\\assignment_six\\src\\main\\java\\com\\example\\assignment_six\\Products.csv");

    public List<Product> importFromCSV() {
        try(BufferedReader br = new BufferedReader(new FileReader(CSVFilePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] arr = line.split(",");
                productList.add(new Product(Integer.parseInt(arr[0]), arr[1], Double.parseDouble(arr[2])));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return productList;
    }

    public String exportToCSV(Product product) {
        System.out.println("\n");
        try (FileWriter fw = new FileWriter(CSVFilePath, true)) {
            fw.append(product.getId() + "," + product.getName() + "," + product.getPrice() + "\n");
        } catch (IOException e) {
            return "Product not added";
        }
        return "Product added";
    }

    public String CSVToDatabase() {
        try(BufferedReader br = new BufferedReader(new FileReader(CSVFilePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                productRepository.save(new Product(Integer.parseInt(arr[0]), arr[1], Double.parseDouble(arr[2])));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Products added to database";
    }

    public String CSVToXML() {
        File XMLFilePath = new File("C:\\Users\\DELL\\IdeaProjects\\BITS Java Training\\assignment_six\\src\\main\\java\\com\\example\\assignment_six\\Products.xml");
        try(BufferedReader br = new BufferedReader(new FileReader(CSVFilePath)); FileWriter fw = new FileWriter(XMLFilePath)) {
            String header=br.readLine();
            if(header==null) {
                return "File is empty";
            }
            String [] headers=header.split(",");
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fw.write("<Products>\n");
            String line;
            while((line=br.readLine())!=null) {
                String [] values=line.split(",");
                fw.write("  <Product>\n");
                for(int i=0;i<headers.length;i++) {
                    fw.write("      <" + headers[i].trim() + ">" + values[i].trim() + "</" + headers[i].trim() + ">\n");
                }
                fw.write("  </Product>\n");
            }
            fw.write("</Products>\n");
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return "CSV converted to XML";
    }
}
