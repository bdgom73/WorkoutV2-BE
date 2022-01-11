package app.workout.Service.ArgumentResolver.Login;

import app.workout.Service.CommonConst;
import app.workout.Service.Jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginCheckArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenService jwtTokenService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasParameterAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasMemberTypeLong = Long.class.isAssignableFrom(parameter.getParameterType());
        boolean hasMemberTypeString = Long.class.isAssignableFrom(parameter.getParameterType());
        return hasParameterAnnotation && (hasMemberTypeLong || hasMemberTypeString) ;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String requestURI = request.getRequestURI();

        String authorization = request.getHeader(CommonConst.AUTHORIZATION);

        try{
            String token = jwtTokenService.extractToken(authorization);
            jwtTokenService.expired(token);
            Long userId = jwtTokenService.getUserInformation(token);
            log.info("Access User [{}][{}]",requestURI,userId);
            return userId;
        }catch (Exception e){
            log.info("Inaccessible User [{}]", requestURI);
            return null;
        }

    }
}
