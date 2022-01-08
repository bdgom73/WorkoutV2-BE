package app.workout.Service.Member;

import app.workout.Entity.Member.BodyData;
import app.workout.Entity.Member.Member;
import app.workout.Repository.BodyDataRepository;
import app.workout.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
            throw new IllegalStateException("존재하지 않는 유저입니다.");
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
           throw  new IllegalStateException("찾을 수 없는 데이터 입니다");
        });
    }

    /**
     * 바디 데이터 업데이트
     * */
    @Transactional
    public Long updateBodyData(Long bodyDataId ,int age, double height, double weight){
        BodyData bodyData = bodyDataRepository.findById(bodyDataId).orElseThrow(() -> {
            throw new IllegalStateException("변경가능한 데이터가 없습니다");
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
    public void deleteBodyData(Long bodyDataId){
//        bodyDataRepository.findById(bodyDataId).orElseThrow();
        bodyDataRepository.deleteById(bodyDataId);
    }

}
