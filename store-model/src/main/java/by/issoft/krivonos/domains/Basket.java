package by.issoft.krivonos.domains;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Basket {
    private Set<OrderItem> orderItems;

    public Basket(Set<OrderItem> orderItemMap) {
        this.orderItems = orderItemMap;
    }
    public Basket() {
        this.orderItems = new HashSet<>();
    }

    public boolean putIn(OrderItem item){
        return orderItems.add(item);
    }
    public void putInAll(OrderItem[] items){
        orderItems.addAll(Arrays.asList(items));
    }

    public boolean putOut(OrderItem item){
        return orderItems.remove(item);
    }

    public Set<String> getAllItemsNames(){
        return orderItems.stream().map(x -> x.getItem().getNameItem()).collect(Collectors.toSet());
    }
    public void clear(){
        orderItems.clear();
    }

    public long amountOfItem(){
        return orderItems.size();
    }

    public int getCost(){
        int sum = 0;
        return orderItems.stream().map(x -> x.getItem().getCost()* x.getAmount()).reduce( sum, Integer::sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(orderItems, basket.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItems);
    }
}
