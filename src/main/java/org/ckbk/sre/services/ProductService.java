package org.ckbk.sre.services;

import org.ckbk.sre.exceptions.EmptyInputFieldException;
import org.ckbk.sre.exceptions.InputNotANumberException;
import org.ckbk.sre.model.Product;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.Objects;

public class ProductService {

    private static ObjectRepository<Product> productRepository;

    public static void initDatabase(Nitrite database) {
        productRepository = database.getRepository(Product.class);
    }

    public static void addProduct(String name, String image, String username, String quantity, String  price, String measurement, String available) throws EmptyInputFieldException, InputNotANumberException {
        checkInputFieldsAreFilled(name, image, quantity, price);
        checkInputIsANumber(quantity, price, available);
        productRepository.insert(new Product(name, image, username, Float.parseFloat(quantity), Float.parseFloat(price), measurement, Float.parseFloat(available)));
    }

    public static Product findProduct(String username, String name){
        for(Product product : productRepository.find())
            if(Objects.equals(product.getUsername(), username) && Objects.equals(product.getName(), name))
                return product;
        return null;
    }

    private static void checkInputFieldsAreFilled(String name, String image, String quantity, String  price) throws EmptyInputFieldException {
        if(name.isEmpty() || image.isEmpty() || quantity.isEmpty() || price.isEmpty()) throw new EmptyInputFieldException();
    }

    private static void checkInputIsANumber(String quantity, String price, String available) throws InputNotANumberException {
        try {
            Float.parseFloat(quantity);
            Float.parseFloat(price);
            Float.parseFloat(available);
        } catch (NumberFormatException nfe) {
            throw new InputNotANumberException();
        }
    }

    public static long getProductRepositorySize(){
        return productRepository.size();
    }
}
