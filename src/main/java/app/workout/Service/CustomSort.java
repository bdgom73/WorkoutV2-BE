package app.workout.Service;

import org.springframework.data.domain.Sort;

import java.util.Objects;

public class CustomSort {
    public static Sort.Order getOrder(String sortString, String direction) {
        Sort.Order order = Objects.equals(direction, "desc") ?  Sort.Order.desc(sortString) : Sort.Order.asc(sortString);
        return order;
    }

}
