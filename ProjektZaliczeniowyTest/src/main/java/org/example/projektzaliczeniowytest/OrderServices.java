package org.example.projektzaliczeniowytest;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServices {
    private final OrderStorage orderStorage;
    private final ProductStorage productStorage;

    public OrderServices(OrderStorage orderStorage, ProductStorage productStorage) {
        this.orderStorage = orderStorage;
        this.productStorage = productStorage;
    }

    //Złożenie zamówienia

    /*
    public Optional<Order> placeOrder(int orderID, int clientID, List<Item> items, String address) {
        if (items.isEmpty()) {
            return Optional.empty();
        } else {
            for (Item item : items) {
                int quantity = item.getQuantity();
                Optional<Product> productOptional = productStorage.getProductByName(item.getProductName());

                if (productOptional.isEmpty()) {
                    return Optional.empty(); // Produkt o danej nazwie nie istnieje
                }

                Product foundProduct = productOptional.get();
                int availableQuantity = foundProduct.getQuantity();

                if (quantity > availableQuantity) {
                    return Optional.empty(); // Niewystarczająca ilość produktu
                } else {
                    foundProduct.setQuantity(availableQuantity - quantity);
                }
            }
            return Optional.of(new Order(orderID, clientID, items, address));
        }
    }
    */

    public Optional<Order> placeOrder(int orderID, int clientID, List<Item> items, String address) {
        if (items == null || items.isEmpty() || items.stream().anyMatch(item -> item.getQuantity() <= 0)) {
            return Optional.empty();
        }

        boolean allProductsAvailable = items.stream()
                .allMatch(item -> {
                    int quantity = item.getQuantity();
                    Product product = productStorage.getProductByName(item.getProductName()).orElseThrow(() -> new RuntimeException("Nie ma takiego produktu"));

                    int availableQuantity = product.getQuantity();

                    if (availableQuantity <= quantity) {
                        return false;
                    } else {
                        product.setQuantity(availableQuantity - quantity);
                        return true;
                    }
                });

        if (allProductsAvailable) {
            Order order = new Order(orderID, clientID, items, address);
            orderStorage.addOrder(orderID,order);
            return Optional.of(order);
        } else {
            return Optional.empty();
        }
    }

    //Status zamówienia

    public Optional<Order> orderStatus(int orderId) {
        Optional<Order> order = orderStorage.findOrderById(orderId);
        return order;
    }

    //Anulowanie zamowienia

    public String orderCancel(int orderId){
        Order order = orderStorage.findOrderById(orderId).orElseThrow(() -> new RuntimeException("Nie znaleziono zamówienia o podanym identyfikatorze."));

        if (order.getOrderStatus() == OrderStatus.W_REALIZACJI) {
            return "Nie można anulować zamówienia w trakcie realizacji.";
        }

        order.setOrderStatus(OrderStatus.ANULOWANE);
        return "Zamówienie zostało anulowane.";
    }

    //Potwierdzenie doręczenia

    public String confirmOrder(int orderId){
        Optional<Order> orderOptional = orderStorage.findOrderById(orderId);

        if (orderOptional.isEmpty()) {
            return "Nie znaleziono zamówienia o podanym identyfikatorze.";
        }

        Order order = orderOptional.get();

        if (order.getOrderStatus() != OrderStatus.W_REALIZACJI) {
            return "Nie można potwierdzić dostarczenia zamówienia, które nie jest w trakcie realizacji.";
        }

        order.setOrderStatus(OrderStatus.DOSTARCZONE);
        return "Dostarczenie zamówienia zostało potwierdzone.";
    }
}
