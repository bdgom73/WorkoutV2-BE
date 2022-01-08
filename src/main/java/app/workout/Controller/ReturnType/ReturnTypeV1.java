package app.workout.Controller.ReturnType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnTypeV1<T> {

    private T data;

}
