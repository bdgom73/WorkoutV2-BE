package app.workout.Controller.Member;

import app.workout.Controller.ReturnType.ReturnTypeV1;
import app.workout.Entity.Member.BodyData;
import app.workout.Service.Member.BodyDataService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class BodyDataController {

    private final BodyDataService bodyDataService;
    private final EntityManager em;

    /**
     * 페이징 바디데이터 리턴
     * */
    @GetMapping("/body-data")
    public ReturnTypeV1<List<BodyDataResponse>> getBodyDataList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        PageRequest pageRequest = PageRequest.of(page, size);
        List<BodyDataResponse> result = bodyDataService.findAll(pageRequest).stream()
                .map(BodyDataResponse::new).collect(Collectors.toList());
        return new ReturnTypeV1<>(result);
    }

    /**
     * 바디데이터 상세 리턴
     * */
    @GetMapping("/body-data/{bodyDataId}")
    public ReturnTypeV1<BodyDataResponse> getBodyData(@PathVariable("bodyDataId") Long bodyDataId){
        BodyData bodyData = bodyDataService.findById(bodyDataId);
        return new ReturnTypeV1<>(new BodyDataResponse(bodyData));
    }

    /**
     * 특정 유저의 가장 최근의 바디데이터값 리턴
     * */
    @GetMapping("/body-data/latest")
    public ReturnTypeV1<BodyDataResponse> OneBodyData(){
        //TODO MEMBER 가져오기
        BodyData bodyData = bodyDataService.findBodyDataByMember(1L);
        return new ReturnTypeV1<>(new BodyDataResponse(bodyData));
    }

    /**
     * 데이터생성
     * */
    @PostMapping("/body-data")
    public ReturnTypeV1<BodyDataResponse> createBodyData(@RequestBody BodyDataRequest bodyDataRequest){
        //TODO MEMBER 가져오기
        bodyDataService.createBodyData(1L,bodyDataRequest.getAge(), bodyDataRequest.getHeight(), bodyDataRequest.getWeight());
        BodyData bodyData = bodyDataService.findBodyDataByMember(1L);
        return new ReturnTypeV1<>(new BodyDataResponse(bodyData));
    }

    /**
     * 데이터수정
     * */
    @PutMapping("/body-data/{bodyDataId}")
    public ReturnTypeV1<BodyDataResponse> updateBodyData(
            @PathVariable("bodyDataId") Long bodyDataId,
            @RequestBody EditBodyDataRequest bodyDataRequest
    ){
        Long bId = bodyDataService.updateBodyData(bodyDataId ,bodyDataRequest.getAge(),bodyDataRequest.getHeight(),bodyDataRequest.getWeight());
        BodyData bodyData = bodyDataService.findById(bId);
        return new ReturnTypeV1<>(new BodyDataResponse(bodyData));
    }

    @Data
    private static class BodyDataRequest{
        @NotBlank
        private int age;
        @NotBlank
        private double height;
        @NotBlank
        private double weight;
    }

    @Data
    private static class BodyDataResponse{

        private Long bodyDateId;
        private int age;
        private double height;
        private double weight;

        public BodyDataResponse(BodyData bodyData) {
            this.bodyDateId = bodyData.getId();
            this.age = bodyData.getAge();
            this.height = bodyData.getHeight();
            this.weight = bodyData.getWeight();
        }
    }

    @Data
    private static class EditBodyDataRequest {
        @NotBlank
        private Long bodyDateId;
        @NotBlank
        private int age;
        @NotBlank
        private double height;
        @NotBlank
        private double weight;
    }



}
