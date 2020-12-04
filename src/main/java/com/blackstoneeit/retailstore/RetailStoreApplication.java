package com.blackstoneeit.retailstore;

import com.blackstoneeit.retailstore.constants.ProductType;
import com.blackstoneeit.retailstore.constants.UserType;
import com.blackstoneeit.retailstore.entity.Cart;
import com.blackstoneeit.retailstore.entity.CartDetail;
import com.blackstoneeit.retailstore.entity.Product;
import com.blackstoneeit.retailstore.entity.User;
import com.blackstoneeit.retailstore.repository.CartRepository;
import com.blackstoneeit.retailstore.repository.ProductRepository;
import com.blackstoneeit.retailstore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@SpringBootApplication
public class RetailStoreApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public static void main(String[] args) {
        SpringApplication.run(RetailStoreApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setName("HP Laptop");
        product.setPrice(BigDecimal.valueOf(10000));
        product.setQuantity(5);
        product.setSku(UUID.randomUUID().toString());
        product.setType(ProductType.ELECTRONICS);

        products.add(product);

        product = new Product();
        product.setName("1 Million Perfume");
        product.setPrice(BigDecimal.valueOf(250));
        product.setQuantity(15);
        product.setSku(UUID.randomUUID().toString());
        product.setType(ProductType.COSMETICS);

        products.add(product);

        product = new Product();
        product.setName("Tuna");
        product.setPrice(BigDecimal.valueOf(15));
        product.setQuantity(10);
        product.setSku(UUID.randomUUID().toString());
        product.setType(ProductType.GROCERIES);

        products.add(product);

        productRepository.saveAll(products);

        User user = new User();

        user.setName("Employee Name");
        user.setType(UserType.EMPLOYEE);
        user.setMobileNumber("0120");

//        user=userRepository.save(user);

        Cart cart = new Cart();

        CartDetail cartDetail = new CartDetail();
        cartDetail.setQuantity(2);
        cartDetail.setProduct(productRepository.findById(1).get());

        cart.addCartDetail(cartDetail);

        cartDetail = new CartDetail();
        cartDetail.setQuantity(1);
        cartDetail.setProduct(productRepository.findById(2).get());

        cart.addCartDetail(cartDetail);

        user.setCart(cart);

        userRepository.save(user);
    }
}
