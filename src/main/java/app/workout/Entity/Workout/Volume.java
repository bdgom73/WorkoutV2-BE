package app.workout.Entity.Workout;

import app.workout.Entity.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Volume extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="volume_id")
    private Long id;
    private int num;
    private int sets;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="workout_id")
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="routine_id")
    private Routine routine;

    protected Volume(){}

    public Volume(int num, int sets) {
        this.num = num;
        this.sets = sets;
    }

    public void changeVolume(int num, int sets){
        this.num = num;
        this.sets = sets;
    }

    //*연관관계 메서드*//
    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
    public void setWorkout(Workout workout){
        this.workout = workout;
    }

    //*생성 메서드*//
    public static Volume createVolume(int num , int sets , Workout workout){
        Volume volume = new Volume(num, sets);
        volume.setWorkout(workout);
        return volume;
    }
}
