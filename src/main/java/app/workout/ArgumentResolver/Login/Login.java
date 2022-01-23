package app.workout.ArgumentResolver.Login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Authorization 헤더에 있는 토큰 확인 사용가능하다면 유저의 ID값 리턴유효토큰이 아닐 시 NULL 리턴</p>
 * */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login{
}
