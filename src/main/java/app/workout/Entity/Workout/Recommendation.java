package app.workout.Entity.Workout;

import app.workout.Entity.BaseEntity;
import app.workout.Entity.Member.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Recommendation extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "recommendation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean recommended;

    protected Recommendation(){}

    protected Recommendation(Routine routine, Member member) {
        this.routine = routine;
        this.member = member;
    }

    public void recommend(){
        this.recommended = true;
    }

    //** 생성메서드 **//
    public static Recommendation doRecommend(Routine routine, Member member){
        Recommendation recommend = new Recommendation(routine,member);
        recommend.recommend();
        return recommend;
    }
}
