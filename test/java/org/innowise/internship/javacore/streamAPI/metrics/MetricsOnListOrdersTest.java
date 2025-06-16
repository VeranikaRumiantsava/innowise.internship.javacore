package org.innowise.internship.javacore.streamAPI.metrics;

import org.innowise.internship.javacore.streamAPI.models.customers.Customer;
import org.innowise.internship.javacore.streamAPI.models.orders.Category;
import org.innowise.internship.javacore.streamAPI.models.orders.Order;
import org.innowise.internship.javacore.streamAPI.models.orders.OrderItem;
import org.innowise.internship.javacore.streamAPI.models.orders.OrderStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetricsOnListOrdersTest {

    //items
    static final OrderItem kunai10 = new OrderItem("Kunai", 10, 2.00, Category.TOYS);
    static final OrderItem kunai5 = new OrderItem("Kunai", 5, 2.00, Category.TOYS);
    static final OrderItem suriken3 = new OrderItem("Suriken", 3, 5.00, Category.TOYS);
    static final OrderItem suriken5 = new OrderItem("Suriken", 5, 5.00, Category.TOYS);
    static final OrderItem suriken8 = new OrderItem("Suriken", 8, 5.00, Category.TOYS);
    static final OrderItem ramen = new OrderItem("Ramen", 1, 13.00, Category.HOME);
    static final OrderItem ichi = new OrderItem("Ichi-ichi", 1, 2.50, Category.BOOKS);
    static final OrderItem jacket = new OrderItem("Jacket", 10, 3.10, Category.CLOTHING);
    static final OrderItem band1 = new OrderItem("Band", 1, 13.00, Category.CLOTHING);
    static final OrderItem band2 = new OrderItem("Band", 2, 13.00, Category.CLOTHING);
    static final OrderItem band3 = new OrderItem("Band", 3, 13.00, Category.CLOTHING);
    static final OrderItem grim = new OrderItem("Grim", 10, 0.30, Category.BEAUTY);

    //customers
    static final Customer naruto = (new Customer("1", "Naruto", "ramen.eater@gmail.com", LocalDateTime.now(),17, "Konoha"));
    static final Customer sasuke = (new Customer("2", "Sasuke", "sasuke@gmail.com", LocalDateTime.now(),17, "Free"));
    static final Customer sakura = (new Customer("3", "Sakura", "konoha.healer@gmail.com", LocalDateTime.now(),17, "Konoha"));
    static final Customer kakashi = (new Customer("4", "Kakashi", "copy.ninja@gmail.com", LocalDateTime.now(),28, "Konoha"));
    static final Customer yamato = (new Customer("5", "Yamato", "mokuton@gmail.com", LocalDateTime.now(),25, "Anbu"));


    //list_orders
    static final List<Order> listOrder = List.of(
        new Order("1", LocalDateTime.now(), naruto, List.of(kunai5, suriken5), OrderStatus.SHIPPED),
        new Order("2", LocalDateTime.now(), naruto, List.of(kunai10, band1, grim), OrderStatus.DELIVERED), //
        new Order("3", LocalDateTime.now(), naruto, List.of(band2), OrderStatus.NEW),
        new Order("4", LocalDateTime.now(), sasuke, List.of(suriken8, kunai10), OrderStatus.CANCELLED),
        new Order("5", LocalDateTime.now(), naruto, List.of(ramen), OrderStatus.PROCESSING),
        new Order("6", LocalDateTime.now(), kakashi, List.of(ichi, kunai10), OrderStatus.SHIPPED),
        new Order("7", LocalDateTime.now(), sakura, List.of(kunai10), OrderStatus.PROCESSING),
        new Order("8", LocalDateTime.now(), yamato, List.of(jacket, suriken3), OrderStatus.NEW),
        new Order("9", LocalDateTime.now(), naruto, List.of(kunai5, suriken8, jacket), OrderStatus.DELIVERED), //
        new Order("10", LocalDateTime.now(), naruto, List.of(band3), OrderStatus.DELIVERED)
    );


    @Test
    public void uniqueCitiesShouldReturnCorrectList() {
        List<String> listUniqueCities = MetricsOnListOrders.uniqueCities(listOrder);

        assertEquals(3, listUniqueCities.size());
        assertTrue(listUniqueCities.contains("Konoha"));
        assertTrue(listUniqueCities.contains("Anbu"));
        assertTrue(listUniqueCities.contains("Free"));
    }

    @Test
    public void uniqueCitiesShouldReturnCorrectListForListWithNull() {
        List<Order> modifiableList = new ArrayList<>(listOrder);
        modifiableList.add(null);
        List<String> listUniqueCities = MetricsOnListOrders.uniqueCities(modifiableList);
        assertEquals(3, listUniqueCities.size());
    }

    @Test
    public void uniqueCitiesShouldReturnEmptyListForNullList() {
        List<String> listUniqueCities = MetricsOnListOrders.uniqueCities(null);
        assertTrue(listUniqueCities.isEmpty());
    }

    @Test
    public void uniqueCitiesShouldReturnEmptyListForEmptyList() {
        List<String> listUniqueCities = MetricsOnListOrders.uniqueCities(new ArrayList<>());
        assertTrue(listUniqueCities.isEmpty());
    }

    @Test
    public void totalIncomeCompletedOrdersShouldReturnCorrectTotalIncome() {
        assertEquals(156.0, MetricsOnListOrders.totalIncomeCompletedOrders(listOrder), 0.001);
    }

    @Test
    public void totalIncomeCompletedOrdersShouldReturnZeroForEmptyList() {
        assertEquals(0.0, MetricsOnListOrders.totalIncomeCompletedOrders(new LinkedList<>()), 0.001);
    }

    @Test
    public void totalIncomeCompletedOrdersShouldReturnZeroForNullList() {
        assertEquals(0.0, MetricsOnListOrders.totalIncomeCompletedOrders(null), 0.001);
    }

    @Test
    public void mostPopularProductShouldReturnCorrectProduct(){
        assertEquals("Kunai", MetricsOnListOrders.mostPopularProduct(listOrder));
    }

    @Test
    public void mostPopularProductShouldReturnNullForEmptyList(){
        assertNull(MetricsOnListOrders.mostPopularProduct(new ArrayList<>()));
    }

    @Test
    public void mostPopularProductShouldReturnNullForNull(){
        assertNull(MetricsOnListOrders.mostPopularProduct(null));
    }

    @Test
    public void mostPopularProductShouldReturnAnyOfEqualPopularProducts(){
        List<Order> modifiableList = new ArrayList<>(listOrder);
        modifiableList.add(new Order("11", LocalDateTime.now(), sakura, List.of(jacket, jacket, jacket), OrderStatus.DELIVERED));

        String answer = MetricsOnListOrders.mostPopularProduct(modifiableList);
        List<String> expected = List.of("Kunai", "Jacket");
        assertTrue(expected.contains(answer));
    }

    @Test
    public void averageCheckDeliveredOrdersShouldReturnCorrectAverage() {
        assertEquals(52.0, MetricsOnListOrders.averageCheckDeliveredOrders(listOrder), 0.001);
    }

    @Test
    public void averageCheckDeliveredOrdersShouldReturnZeroForEmptyList() {
        assertEquals(0.0, MetricsOnListOrders.averageCheckDeliveredOrders(new ArrayList<>()), 0.001);
    }

    @Test
    public void averageCheckDeliveredOrdersShouldReturnZeroForNull() {
        assertEquals(0.0, MetricsOnListOrders.averageCheckDeliveredOrders(null), 0.001);
    }

    @Test
    public void averageCheckDeliveredOrdersShouldReturnZeroForListWithoutDeliveredOrders() {
        assertEquals(0.0, MetricsOnListOrders.averageCheckDeliveredOrders(
                List.of(new Order("11", LocalDateTime.now(), sakura, List.of(jacket), OrderStatus.NEW))
        ), 0.001);
    }

    @Test
    public void customersWithMoreThanFiveOrdersShouldReturnCorrectList(){
        List<Customer> suchActiveCustomers = MetricsOnListOrders.customersWithMoreThanFiveOrders(listOrder);
        assertEquals(1, suchActiveCustomers.size());
        assertEquals("1", suchActiveCustomers.getFirst().getCustomerId());
    }

    @Test
    public void customersWithMoreThanFiveOrdersShouldReturnEmptyListForListWithoutCustomersWithMoreThenFiveOrders(){
        List<Customer> suchActiveCustomers = MetricsOnListOrders.customersWithMoreThanFiveOrders(
                List.of(new Order("11", LocalDateTime.now(), sakura, List.of(jacket), OrderStatus.NEW)));
        assertEquals(0, suchActiveCustomers.size());
    }

    @Test
    public void customersWithMoreThanFiveOrdersShouldReturnEmptyListForNull(){
        assertTrue(MetricsOnListOrders.customersWithMoreThanFiveOrders(null).isEmpty());
    }

    @Test
    public void customersWithMoreThanFiveOrdersShouldReturnEmptyListForEmptyList(){
        assertTrue(MetricsOnListOrders.customersWithMoreThanFiveOrders(new ArrayList<>()).isEmpty());
    }


}
