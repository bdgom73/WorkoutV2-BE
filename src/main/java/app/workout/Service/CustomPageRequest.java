package app.workout.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class CustomPageRequest {

    public static PageRequest getPageRequest(int page, int size, String sortString, String direction) {
        return PageRequest.of(page, size, SortDirection(direction), sortString);
    }

    private static Sort.Direction SortDirection(String direction) {
        return Sort.Direction.valueOf(direction.toUpperCase() == "DESC" ? "DESC" : "AES");
    }
}
