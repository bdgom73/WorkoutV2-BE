package app.workout.Service.Member;

import app.workout.Entity.Member.ApiClient;
import app.workout.Entity.Member.Member;
import app.workout.Messages.ErrorMessages;
import app.workout.Repository.Member.ApiClientRepository;
import app.workout.Repository.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ApiClientService {

    private final MemberRepository memberRepository;
    private final ApiClientRepository apiClientRepository;

    public ApiClient findById(Long clientId){
        return apiClientRepository.findById(clientId).orElseThrow(() -> {
            throw new IllegalArgumentException(ErrorMessages.NOT_EXISTS_ISSIE_KEY);
        });
    }

    public ApiClient findOneByMember(Long memberId){
        return apiClientRepository.findByMember(memberId).orElseThrow(() -> {
            throw new IllegalArgumentException(ErrorMessages.NOT_FOUND_USER);
        });
    }

    public ApiClient findByKey(String key){
        return apiClientRepository.findByApiKey(key).orElseThrow(() -> {
            throw new IllegalStateException(ErrorMessages.INVALID_KEY);
        });
    }

    public boolean checkApiKey(String key){
        if(key == null) {
            throw new IllegalStateException(ErrorMessages.NOT_FOUND_KEY);
        }
        findByKey(key);
        return true;
    }

    @Transactional
    public ApiClient issueApiKey(Long memberId){
        String key ;
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalStateException(ErrorMessages.NOT_FOUND_USER);
        });

        if(member.getApiClient() != null){
            throw new IllegalStateException(ErrorMessages.EXISTS_ISSIE_KEY_USER);
        }

        while (true){
            key =  RandomString.make(39);
            Optional<ApiClient> findKey = apiClientRepository.findByApiKey(key);

            if(findKey.isEmpty()){
                break;
            }
        }

        ApiClient apiClient = ApiClient.createApiClient(key, member);
        apiClientRepository.save(apiClient);
        return apiClient;

    }

    @Transactional
    public void cancelApiKey(Long memberId){
        ApiClient client = findOneByMember(memberId);
        apiClientRepository.delete(client);
    }


}
