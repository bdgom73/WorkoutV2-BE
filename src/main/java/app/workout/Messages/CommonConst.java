package app.workout.Messages;


import org.apache.tomcat.util.http.parser.Authorization;

public interface CommonConst {
    String AUTHORIZATION = "Authorization";
    String CLIENT_API_KEY = "CLIENT_API_KEY";

    String LOGIN_MEMBER = "loginMember";

    String JWT_PROPERTIES = "classpath:variable/jwtt.properties";
}
