package app.workout.Interceptor;

import app.workout.Messages.CommonConst;
import app.workout.Service.Member.ApiClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientKeyInterceptor implements HandlerInterceptor {

    private final ApiClientService apiClientService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        String apiKey = request.getHeader(CommonConst.CLIENT_API_KEY);
        log.info("ClientAPI Access Check [{}][{}][{}]", requestURI, apiKey, handler);
        return apiClientService.checkApiKey(apiKey);
    }

}
