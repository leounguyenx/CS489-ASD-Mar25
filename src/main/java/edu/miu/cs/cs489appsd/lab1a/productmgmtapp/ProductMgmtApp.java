package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class ProductMgmtApp {
    public static void main(String[] args) throws IOException {
        Product[] products = {
                new Product(3128874119L, "Banana", "2023-01-24", 124, 0.55),
                new Product(2927458265L, "Apple", "2022-12-09", 18, 1.09),
                new Product(9189927460L, "Carrot", "2023-03-31", 89, 2.99)
        };
        Arrays.sort(products, Comparator.comparingDouble(Product::getUnitPrice).reversed());

        System.out.println(Arrays.asList(products));

        printProductsJSON(products);

        printProductsXML(products);

        printProductsCSV(products);
    }

    private static void printProductsJSON(Product[] products) throws JsonProcessingException {
        System.out.println("-------------JSON Format----------");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
        System.out.println(jsonOutput);
    }

    private static void printProductsXML(Product[] products) throws IOException {
        System.out.println("-------------XML Format----------");
        XmlMapper xmlMapper = new XmlMapper();
        String xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
        System.out.println(xmlOutput);
    }

    private static void printProductsCSV(Product[] products) {
        System.out.println("-------------CSV Format----------");
        System.out.println("ProductId,Name,DateSupplied,QuantityInStock,UnitPrice");
        for (Product p : products) {
            System.out.printf("%d,%s,%s,%d,%.2f%n",
                    p.getProductId(), p.getName(), p.getDateSupplied(), p.getQuantityInStock(), p.getUnitPrice());
        }
    }

}
