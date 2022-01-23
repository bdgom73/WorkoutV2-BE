package app.workout;

import app.workout.ArgumentResolver.Login.LoginCheckArgumentResolver;
import app.workout.Interceptor.ClientKeyInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginCheckArgumentResolver loginCheckArgumentResolver;
    private final ClientKeyInterceptor clientKeyInterceptor;



    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginCheckArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] commonExcludePathPatterns = {"/css/**", "/*.ico", "/error", "/api/login", "/api/join"};

        registry.addInterceptor(clientKeyInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(commonExcludePathPatterns);
    }
}
