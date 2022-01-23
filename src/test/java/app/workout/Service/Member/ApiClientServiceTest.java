package app.workout.Service.Member;

import app.workout.Entity.Member.ApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ApiClientServiceTest {

    @Autowired
    ApiClientService apiClientService;

    @Test
    void createClientKeyTest(){
        ApiClient client = apiClientService.issueApiKey(1L);
        System.out.println("client = " + client.getApiKey());

        ApiClient client2 = apiClientService.issueApiKey(2L);
        System.out.println("client2 = " + client2.getApiKey());

    }
}