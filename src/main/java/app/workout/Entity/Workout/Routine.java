package app.workout.Entity.Workout;

import app.workout.Entity.BaseEntity;
import app.workout.Entity.Member.Member;
import app.workout.Entity.Workout.Eunm.ExercisePart;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Routine extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "routine_id")
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private ExercisePart part;
    private boolean share;

    @Column(name="original_author")
    private Long originalAuthor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    private List<Volume> volumes = new ArrayList<>();

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations = new ArrayList<>();

    public Routine(){}

    public Routine(String title, ExercisePart part, boolean share) {
        this.title = title;
        this.part = part;
        this.share = share;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

    //*연관관계 메서드*//
    public void changeMember(Member member){
        this.member = member;
    }
    public void addVolumes(Volume volume){
        volumes.add(volume);
        volume.setRoutine(this);
    }
    public void changeRoutine(String title, ExercisePart part){
        this.title = title;
        this.part = part;
    }
    private void setOriginalAuthor(Long originalAuthorId){
        originalAuthor = originalAuthorId;
    }

    public void recommend(Member member){
        Recommendation recommendation = Recommendation.doRecommend(this, member);
        this.recommendations.add(recommendation);
    }

    //*생성 메서드*//
    public static Routine createRoutine(String title, ExercisePart part, boolean share ,Member member, Volume... volumes){
        Routine routine = new Routine(title,part,share);
        routine.changeMember(member);
        routine.setOriginalAuthor(member.getId());
        for (Volume v : volumes) {
            routine.addVolumes(v);
        }
        return routine;
    }
    public static Routine createRoutine(String title, ExercisePart part, boolean share ,Member member, List<Volume> volumes){
        Routine routine = new Routine(title,part,share);
        routine.changeMember(member);
        for (Volume v : volumes) {
            routine.addVolumes(v);
        }
        return routine;
    }

    public static Routine copyRoutine(Long originalAuthor ,String title, ExercisePart part ,Member member, List<Volume> volumes){
        Routine routine = new Routine(title,part,false);
        routine.setOriginalAuthor(originalAuthor);
        routine.changeMember(member);
        for (Volume v : volumes) {
            routine.addVolumes(v);
        }
        return routine;
    }


}
