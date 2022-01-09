package app.workout.Controller.ReturnType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BasicReturnType<T> {
    private T data;

    public BasicReturnType(T data) {
        this.data = data;
    }

    public BasicReturnType() {
    }
}
