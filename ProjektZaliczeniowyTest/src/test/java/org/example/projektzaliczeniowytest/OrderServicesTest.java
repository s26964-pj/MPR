package org.example.projektzaliczeniowytest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServicesTest {

    @InjectMocks
    OrderServices orderServices;
    @Mock
    OrderStorage orderStorage;

    @Test
    void shouldOrderHasNewStatus() {
        //given
        Order order = newOrder(1
                ,2
                ,List.of(new OrderItem(
                        new Product(
                                "Jajecznica",
                                BigDecimal.valueOf(17)),
                                5)
                )
        );
        //when
        when(orderStorage.findOrderById(anyInt())).thenReturn(Optional.of(order));
        Order statusedOrder = orderServices.statusOrder(anyInt());
        //then
        assertEquals(OrderStatus.NOWE,statusedOrder.getOrderStatus());
    }
    private Order newOrder(int orderID ,int clientID, List<OrderItem> orderItems) {
        Order order = new Order(orderID,clientID,orderItems);
        return order;
    }

}