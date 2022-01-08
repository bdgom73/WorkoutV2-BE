package app.workout.Entity.Calendar;

import app.workout.Entity.Member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class ExerciseRecord {

    @Id @GeneratedValue
    @Column(name = "exercise_recode_id")
    private Long id;

    private boolean isWorkout;
    private LocalDate date;
    @Column(columnDefinition = "TEXT")
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public ExerciseRecord(boolean isWorkout, LocalDate date, String memo) {
        this.isWorkout = isWorkout;
        this.date = date;
        this.memo = memo;
    }

    public void setMember(Member member){
        this.member = member;
    }

    //*메서드*//
    public void changeExerciseRecode(LocalDate date, String memo){
        this.date = date;
        this.memo = memo;
    }

    public void doExercise(){
        isWorkout = Boolean.TRUE;
    }
    public void doNotExercise(){
        isWorkout = Boolean.FALSE;
    }

}
