package app.workout.Service.Routine;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateVolumeDTO {

    @NotBlank
    private int num;
    @NotBlank
    private int sets;
    @NotBlank
    private Long workoutId;

    public CreateVolumeDTO(){}

    public CreateVolumeDTO(int num, int sets, Long workoutId) {
        this.num = num;
        this.sets = sets;
        this.workoutId = workoutId;
    }
}
