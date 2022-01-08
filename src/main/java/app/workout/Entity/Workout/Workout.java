package app.workout.Entity.Workout;

import app.workout.Entity.BaseEntity;
import app.workout.Entity.Workout.Eunm.ExercisePart;
import app.workout.Entity.Workout.Eunm.ExerciseType;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Workout extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "workout_id")
    private Long id;
    private String name;
    private String imageUrl;
    @Enumerated(EnumType.ORDINAL)
    private ExercisePart part;
    @Enumerated(EnumType.ORDINAL)
    private ExerciseType type;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    protected Workout(){}

    public Workout(String name, ExercisePart part, ExerciseType type) {
        this.name = name;
    }

    public void changeWorkout(String name, ExercisePart part, ExerciseType type){
        this.name = name;
        this.part = part;
        this.type = type;
    }

    public void changeImage(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void changeExplanation(String explanation){
        this.explanation = explanation;
    }
}
