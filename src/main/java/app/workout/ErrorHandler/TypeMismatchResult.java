package app.workout.ErrorHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TypeMismatchResult {

    private LocalDateTime timestamp;
    private int status;
    private HttpStatus error;
    private String path;
    private String message;
    private String exception;
    private String requiredType;
    private String valueType;
    private String value;

}
