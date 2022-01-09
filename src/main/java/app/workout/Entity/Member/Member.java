package app.workout.Entity.Member;

import app.workout.Entity.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String name;
    private String nickname;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BodyData> bodyData = new ArrayList<>();

    public Member() {
    }

    public Member(String email, String password, String name, String nickname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }

    public void addBodyData(BodyData bodyData){
        this.bodyData.add(bodyData);
    }

    public void changeMember(String name, String nickname){
        this.name = name;
        this.nickname = nickname;
    }

}
