package app.workout.Controller.ReturnType;

import lombok.Data;

public class ReturnTypeV1<T> extends BasicReturnType<T> {

    public ReturnTypeV1(T data) {
        super(data);
    }

}
