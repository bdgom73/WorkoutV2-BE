package app.workout.Entity.Member;

import app.workout.Entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "body_data")
public class BodyData extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="body_data_id")
    private Long id;
    private int age;
    private double height;
    private double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    public BodyData() {
    }

    public BodyData(int age, double height, double weight) {
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public void addMember(Member member){
        member.addBodyData(this);
        this.member = member;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}