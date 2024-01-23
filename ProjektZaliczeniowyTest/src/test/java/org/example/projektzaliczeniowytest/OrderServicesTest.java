package org.example.projektzaliczeniowytest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServicesTest {

    @InjectMocks
    OrderServices orderServices;
    @Mock
    OrderStorage orderStorage;
    @Mock
    ProductStorage productStorage;

    @Test
    void shouldCreateOrder() {
        int orderID = 1;
        int clientID = 2;
        List<Item> items = new ArrayList<>(List.of(
                new Item("Zurek", 1)));
        String address = "ul. Testowa 123";

        when(productStorage.getProductByName(anyString())).thenReturn(Optional.of(
                new Product("Zurek", 5)
        ));

        Optional<Order> result = orderServices.placeOrder(orderID,clientID,items,address);

        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotCreateOrder() {
        int orderID = 1;
        int clientID = 2;
        List<Item> items = new ArrayList<>(List.of(
                new Item("Zurek", 7)));
        String address = "ul. Testowa 123";

        when(productStorage.getProductByName(anyString())).thenReturn(Optional.of(
                new Product("Zurek", 5)
        ));

        Optional<Order> result = orderServices.placeOrder(orderID,clientID,items,address);

        assertFalse(result.isPresent());
    }

    private Order newOrder() {
        return new Order(
                1,
                2,
                new ArrayList<>(List.of(
                        new Item("Jajecznica", 5),
                        new Item("Zurek", 15)))
                , "Gdańsk, Polna");
    }
}