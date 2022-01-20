package app.workout.Service.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDataDto {

    private Long memberId;
    private String email;
    private String name;
    private String nickname;

    private Long bodyDataId;
    private int age;
    private double height;
    private double weight;

}
