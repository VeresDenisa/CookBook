package org.ckbk.sre.services;

import org.ckbk.sre.exceptions.EmptyInputFieldException;
import org.ckbk.sre.exceptions.InputNotANumberException;
import org.ckbk.sre.model.Product;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;


public class ProductService {

    private static ObjectRepository<Product> productRepository;

    public static void initDatabase(Nitrite database) {
        productRepository = database.getRepository(Product.class);
    }

    public static void addProduct(String name, String image, String username, String quantity, String  price) throws EmptyInputFieldException, InputNotANumberException {
        checkInputFieldsAreFilled(name, image, quantity, price);
        checkInputIsANumber(quantity, price);
        productRepository.insert(new Product(name, image, username, Float.parseFloat(quantity), Float.parseFloat(price)));
    }

    private static void checkInputFieldsAreFilled(String name, String image, String quantity, String  price) throws EmptyInputFieldException {
        if(name.isEmpty() || image.isEmpty() || quantity.isEmpty() || price.isEmpty()) throw new EmptyInputFieldException();
    }

    private static void checkInputIsANumber(String quantity, String price) throws InputNotANumberException {
        try {
            Float.parseFloat(quantity);
            Float.parseFloat(price);
        } catch (NumberFormatException nfe) {
            throw new InputNotANumberException();
        }
    }

    public static Product getProduct(long i){
        for (Product product : productRepository.find()) {
            if(product.getProductId() == i)
                return product;
        }
        return null;
    }

    public static long getProductRepositorySize(){
        return productRepository.size();
    }
}
