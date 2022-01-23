package app.workout.Entity.Member;

import app.workout.Entity.BaseEntity;
import lombok.Getter;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String avatarUrl;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private RoleType role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BodyData> bodyData = new ArrayList<>();

    @OneToOne(mappedBy = "member")
    private ApiClient apiClient;


    public Member() {
        this.role = RoleType.USER;
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
    public void addApiKey(ApiClient client){
        apiClient = client;
    }

    public void changeMember(String name, String nickname){
        this.name = name;
        this.nickname = nickname;
    }

    public void changeAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }

}
