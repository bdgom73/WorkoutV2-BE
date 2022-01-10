package app.workout.Service.Jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenLoginDto {
    private Long memberId;
    private String name;
    private String nickname;

    public TokenLoginDto(Long memberId, String name, String nickname) {
        this.memberId = memberId;
        this.name = name;
        this.nickname = nickname;
    }

    public boolean isEmpty(){
        return memberId == null;
    }
    public boolean compare(Long compareId){
        return memberId.equals(compareId);
    }
}
