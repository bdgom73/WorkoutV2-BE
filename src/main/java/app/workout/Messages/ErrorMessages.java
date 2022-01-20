package app.workout.Messages;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

public interface ErrorMessages {
    
   String NO_LOGIN_USER = "로그인 정보가 존재하지 않습니다";
   
   String NOT_FOUND_USER = "찾을 수 없는 유저 입니다";

   String NO_PERMISSION = "권한이 없습니다";

   String NOT_FOUND_SCHEDULE = "일정을 찾을 수 없습니다";

   String NOT_FOUND_RECODE = "기록을 찾을 수 없습니다";

   String EXPIRED_TOKEN = "만료된 토큰입니다";

   String INVALID_TOKEN = "유효하지 않은 토큰입니다";

   String NOT_FOUND_TOKEN = "토큰을 찾을 수 없습니다";

   String NOT_FOUND_DATA = "찾을 수 없는 데이터 입니다";

   String EXISTS_EMAIL = "이미 존재하는 이메일 입니다";

   String ERROR_REGEX_PASSWORD = "비밀번호는 10자리 이상에 특수문자가 포함되어야합니다";

   String NOT_FOUND_ROUTINE = "찾을 수 없는 루틴 입니다";

   String NOT_SHAREABLE = "공유가 불가능 합니다";

   String EXISTS_RECOMMEND = "이미 추천했습니다";

   String NOT_FOUND_RECOMMEND = "추천 취소를 할 수 없습니다";

   String NOT_FOUND_VOLUME  = "볼륨을 찾을 수 없습니다";


}
