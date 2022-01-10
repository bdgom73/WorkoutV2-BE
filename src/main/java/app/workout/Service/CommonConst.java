package app.workout.Service;


import org.apache.tomcat.util.http.parser.Authorization;

public interface CommonConst {
    String AUTHORIZATION = "Authorization";
    String LOGIN_MEMBER = "loginMember";

    String JWT_PROPERTIES = "classpath:variable/jwtt.properties";
}
