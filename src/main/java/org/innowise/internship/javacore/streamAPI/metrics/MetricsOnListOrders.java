package org.innowise.internship.javacore.streamAPI.metrics;

import org.innowise.internship.javacore.streamAPI.models.customers.Customer;
import org.innowise.internship.javacore.streamAPI.models.orders.Order;
import org.innowise.internship.javacore.streamAPI.models.orders.OrderItem;
import org.innowise.internship.javacore.streamAPI.models.orders.OrderStatus;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final public class MetricsOnListOrders {
    public static List<String> uniqueCities(List<Order> orders){
        if (orders == null || orders.isEmpty())
            return Collections.emptyList();
        return orders.stream()
                .filter(Objects::nonNull)
                .map(order -> order.getCustomer().getCity())
                .distinct()
                .toList();
    }

    public static double totalIncomeCompletedOrders(List<Order> orders){
        if (orders == null || orders.isEmpty())
            return 0;
        return orders.stream().
                filter(Objects::nonNull).
                filter(order -> order.getStatus() == OrderStatus.DELIVERED).
                flatMap(order -> order.getItems().stream()).
                mapToDouble(order -> order.getPrice()*order.getQuantity()).
                sum();
    }

    public static String mostPopularProduct(List<Order> orders){
        if (orders == null || orders.isEmpty())
            return null;
        //filter by status?
        Stream<Order> ordersStream = orders.stream();
        return ordersStream
                .filter(Objects::nonNull)
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getProductName, Collectors.summingInt(OrderItem::getQuantity)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static double averageCheckDeliveredOrders(List<Order> orders){
        if (orders == null || orders.isEmpty())
            return 0;
        return orders.stream()
                .filter(Objects::nonNull)
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(order ->
                                order.getItems().stream()
                                .mapToDouble(orderItem -> orderItem.getPrice()*orderItem.getQuantity())
                                .sum())
                        .average().orElse(0.0);
    }

    public static List<Customer> customersWithMoreThanFiveOrders(List<Order> orders){
        if (orders == null || orders.isEmpty())
            return Collections.emptyList();
        return orders.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
