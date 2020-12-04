package com.blackstoneeit.retailstore;

import com.blackstoneeit.retailstore.constants.ProductType;
import com.blackstoneeit.retailstore.constants.UserType;
import com.blackstoneeit.retailstore.dto.CartDto;
import com.blackstoneeit.retailstore.dto.OrderDto;
import com.blackstoneeit.retailstore.entity.Cart;
import com.blackstoneeit.retailstore.entity.CartDetail;
import com.blackstoneeit.retailstore.entity.Product;
import com.blackstoneeit.retailstore.entity.User;
import com.blackstoneeit.retailstore.repository.CartRepository;
import com.blackstoneeit.retailstore.service.impl.CheckoutServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.blackstoneeit.retailstore.constants.ApplicationConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceTest {

    @InjectMocks
    private CheckoutServiceImpl checkoutService;

    @Mock
    private CartRepository cartRepository;

    @Test(expected = RuntimeException.class)
    public void checkoutNonExistCartThrows() {
        CartDto cartDto = new CartDto();
        cartDto.setId(1);
        when(cartRepository.findById(1)).thenReturn(Optional.empty());
        checkoutService.checkout(cartDto);
    }

    @Test
    public void checkoutEmployeeCartGetDiscount() {
        CartDto cartDto = new CartDto();
        cartDto.setId(1);

        User user = new User();
        user.setType(UserType.EMPLOYEE);
        user.setCreatedAt(LocalDateTime.of(2020, 12, 4, 12, 12, 12));

        Product product = new Product();
        product.setId(1);
        product.setPrice(BigDecimal.valueOf(90));

        Cart cart = new Cart();
        cart.setUser(user);

        CartDetail cartDetail = new CartDetail();
        cartDetail.setId(1);
        cartDetail.setQuantity(1);
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);

        cart.addCartDetail(cartDetail);

        when(cartRepository.findById(1)).thenReturn(Optional.of(cart));

        OrderDto order = checkoutService.checkout(cartDto);

        BigDecimal totalPrice = cart.getCartDetails()
                .stream()
                .map(cartDetail1 -> cartDetail1.getProduct().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expectedDiscount = totalPrice.multiply(BigDecimal.valueOf(EMPLOYEE_DISCOUNT_PERCENTAGE));

        BigDecimal expectedTotalAfterDiscount = totalPrice.subtract(expectedDiscount);

        assertThat(order.getDiscount()).isEqualTo(expectedDiscount);

        assertThat(order.getAfterDiscount()).isEqualTo(expectedTotalAfterDiscount);
    }

    @Test
    public void checkoutEmployeeGroceriesCartGetNoDiscount() {
        CartDto cartDto = new CartDto();
        cartDto.setId(1);

        User user = new User();
        user.setType(UserType.EMPLOYEE);
        user.setCreatedAt(LocalDateTime.of(2020, 12, 4, 12, 12, 12));

        Product product = new Product();
        product.setId(1);
        product.setType(ProductType.GROCERIES);
        product.setPrice(BigDecimal.valueOf(90));

        Cart cart = new Cart();
        cart.setUser(user);

        CartDetail cartDetail = new CartDetail();
        cartDetail.setId(1);
        cartDetail.setQuantity(1);
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);

        cart.addCartDetail(cartDetail);

        when(cartRepository.findById(1)).thenReturn(Optional.of(cart));

        OrderDto order = checkoutService.checkout(cartDto);

        BigDecimal totalPrice = cart.getCartDetails()
                .stream()
                .map(cartDetail1 -> cartDetail1.getProduct().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expectedDiscount = BigDecimal.valueOf(0.0);

        BigDecimal expectedTotalAfterDiscount = totalPrice.subtract(expectedDiscount);

        assertThat(order.getDiscount()).isEqualTo(expectedDiscount);

        assertThat(order.getAfterDiscount()).isEqualTo(expectedTotalAfterDiscount);
    }

    @Test
    public void checkoutAffiliateCartGetDiscount() {
        CartDto cartDto = new CartDto();
        cartDto.setId(1);

        User user = new User();
        user.setType(UserType.AFFILIATE);
        user.setCreatedAt(LocalDateTime.of(2020, 12, 4, 12, 12, 12));

        Product product = new Product();
        product.setId(1);
        product.setPrice(BigDecimal.valueOf(90));

        Cart cart = new Cart();
        cart.setUser(user);

        CartDetail cartDetail = new CartDetail();
        cartDetail.setId(1);
        cartDetail.setQuantity(1);
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);

        cart.addCartDetail(cartDetail);

        when(cartRepository.findById(1)).thenReturn(Optional.of(cart));

        OrderDto order = checkoutService.checkout(cartDto);

        BigDecimal totalPrice = cart.getCartDetails()
                .stream()
                .map(cartDetail1 -> cartDetail1.getProduct().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expectedDiscount = totalPrice.multiply(BigDecimal.valueOf(AFFILIATE_DISCOUNT_PERCENTAGE));

        BigDecimal expectedTotalAfterDiscount = totalPrice.subtract(expectedDiscount);

        assertThat(order.getDiscount()).isEqualTo(expectedDiscount);

        assertThat(order.getAfterDiscount()).isEqualTo(expectedTotalAfterDiscount);
    }

    @Test
    public void checkoutOldCustomerCartGetDiscount() {
        CartDto cartDto = new CartDto();
        cartDto.setId(1);

        User user = new User();
        user.setType(UserType.CUSTOMER);
        user.setCreatedAt(LocalDateTime.of(2017, 12, 4, 12, 12, 12));

        Product product = new Product();
        product.setId(1);
        product.setPrice(BigDecimal.valueOf(90));

        Cart cart = new Cart();
        cart.setUser(user);

        CartDetail cartDetail = new CartDetail();
        cartDetail.setId(1);
        cartDetail.setQuantity(1);
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);

        cart.addCartDetail(cartDetail);

        when(cartRepository.findById(1)).thenReturn(Optional.of(cart));

        OrderDto order = checkoutService.checkout(cartDto);

        BigDecimal totalPrice = cart.getCartDetails()
                .stream()
                .map(cartDetail1 -> cartDetail1.getProduct().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expectedDiscount = totalPrice.multiply(BigDecimal.valueOf(OLD_CUSTOMER_DISCOUNT_PERCENTAGE));

        BigDecimal expectedTotalAfterDiscount = totalPrice.subtract(expectedDiscount);

        assertThat(order.getDiscount()).isEqualTo(expectedDiscount);

        assertThat(order.getAfterDiscount()).isEqualTo(expectedTotalAfterDiscount);
    }

    @Test
    public void checkoutHundredMultiplesCartGetDiscount() {
        CartDto cartDto = new CartDto();
        cartDto.setId(1);

        User user = new User();
        user.setType(UserType.CUSTOMER);
        user.setCreatedAt(LocalDateTime.of(2019, 12, 4, 12, 12, 12));

        Product product = new Product();
        product.setId(1);
        product.setPrice(BigDecimal.valueOf(990));

        Cart cart = new Cart();
        cart.setUser(user);

        CartDetail cartDetail = new CartDetail();
        cartDetail.setId(1);
        cartDetail.setQuantity(1);
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);

        cart.addCartDetail(cartDetail);

        when(cartRepository.findById(1)).thenReturn(Optional.of(cart));

        OrderDto order = checkoutService.checkout(cartDto);

        BigDecimal totalPrice = cart.getCartDetails()
                .stream()
                .map(cartDetail1 -> cartDetail1.getProduct().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expectedDiscount = BigDecimal.valueOf(totalPrice.intValue() / 100 * HUNDRED_MULTIPLES_DISCOUNT_AMOUNT);

        BigDecimal expectedTotalAfterDiscount = totalPrice.subtract(expectedDiscount);

        assertThat(order.getDiscount()).isEqualTo(expectedDiscount);

        assertThat(order.getAfterDiscount()).isEqualTo(expectedTotalAfterDiscount);
    }


}
