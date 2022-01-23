package app.workout.Entity.Member;

import app.workout.Entity.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ApiClient extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "api_client_id")
    private Long id;
    private String apiKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public ApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    protected ApiClient(){

    }

    public void setMember(Member member){
        this.member = member;
        member.addApiKey(this);
    }

    public static ApiClient createApiClient(String key , Member member){
        ApiClient client = new ApiClient(key);
        client.setMember(member);
        return client;
    }
}
