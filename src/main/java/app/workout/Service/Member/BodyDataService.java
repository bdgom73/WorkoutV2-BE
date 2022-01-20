package app.workout.Service.Member;

import app.workout.Entity.Member.BodyData;
import app.workout.Entity.Member.Member;
import app.workout.Messages.ErrorMessages;
import app.workout.Repository.Member.BodyDataRepository;
import app.workout.Repository.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BodyDataService {

    private final BodyDataRepository bodyDataRepository;
    private final MemberRepository memberRepository;
    /**
     * 바디데이터 추가
     * */
    @Transactional
    public Long createBodyData(Long memberId, int age , double height, double weight){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalStateException(ErrorMessages.NOT_FOUND_USER);
        });
        BodyData bodyData = new BodyData(age, height, weight);
        bodyData.addMember(member);
        bodyDataRepository.save(bodyData);
        return bodyData.getId();
    }

    /**
     * 가장 최근의 저장된 바디 데이터 찾기
     * */
    public BodyData findBodyDataByMember(Long memberId){
        PageRequest pageRequest = PageRequest.of(0, 1);
        List<BodyData> bodyDataList = bodyDataRepository.findByMember(memberId, pageRequest);
        return bodyDataList.size() > 0 ? bodyDataList.get(0) : new BodyData();
    }

    /**
     * 바디 데이터 다중 찾기
     * */
    public List<BodyData> findAll(Pageable pageable){
        return bodyDataRepository.findAll(pageable).get().collect(Collectors.toList());
    }

    /**
     * 단일 바디 데이터 찾기
     * */
    public BodyData findById(Long bodyDataId){
        return bodyDataRepository.findById(bodyDataId).orElseThrow(()-> {
           throw  new IllegalStateException(ErrorMessages.NOT_FOUND_DATA);
        });
    }

    /**
     * 바디 데이터 업데이트
     * */
    @Transactional
    public Long updateBodyData(Long bodyDataId ,int age, double height, double weight){
        BodyData bodyData = bodyDataRepository.findById(bodyDataId).orElseThrow(() -> {
            throw new IllegalStateException(ErrorMessages.NOT_FOUND_DATA);
        });
        bodyData.setAge(age);
        bodyData.setHeight(height);
        bodyData.setWeight(weight);
        return bodyData.getId();
    }

    /**
     * 바디 데이터 삭제
     * */
    @Transactional
    public void deleteBodyData(Long bodyDataId, Long memberId){
        BodyData bodyData = bodyDataRepository.findByIdJoinMember(bodyDataId).orElseThrow();
        if(!Objects.equals(bodyData.getMember().getId(), memberId)){
            throw new IllegalStateException(ErrorMessages.NO_PERMISSION);
        }
        bodyDataRepository.deleteById(bodyDataId);
    }
    @Transactional
    public void deleteBodyData(Long bodyDataId){
        bodyDataRepository.deleteById(bodyDataId);
    }

}
