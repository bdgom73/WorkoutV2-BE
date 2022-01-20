package app.workout.Entity.Calendar;

import app.workout.Entity.BaseEntity;
import app.workout.Entity.Member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Calendar extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "calendar_id")
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    private String title;
    @Column(columnDefinition = "text")
    private String memo;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Calendar() {
    }

    public void setDate(LocalDate start , LocalDate end){
        startDate = start;
        endDate = end;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setContent(String title, String memo, String color){
        this.title = title;
        this.memo = memo;
        this.color = color;
    }
}
