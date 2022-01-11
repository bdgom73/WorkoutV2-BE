package app.workout.Controller.Calendar;

import app.workout.Controller.ReturnType.ReturnTypeV1;
import app.workout.Entity.Calendar.Calendar;
import app.workout.Service.ArgumentResolver.Login.Login;
import app.workout.Service.Calendar.CalendarService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class CalendarController {

    private final CalendarService calendarService;
    /**
     * 범위의 스케쥴 정보 가져오기
     * */
    @GetMapping("/schedule")
    public ReturnTypeV1<List<CalendarRequest>> rangeSchedule(
            @RequestParam(value = "start", defaultValue = "#{T(java.time.LocalDateTime).now()}")
            @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(value = "end", defaultValue = "#{T(java.time.LocalDateTime).now()}")
            @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @Login Long memberId
    ){
        if(memberId == null) throw new IllegalStateException("로그인 정보가 존재하지 않습니다");
        List<Calendar> calendars = calendarService.rangeSchedule(memberId, start, end);
        List<CalendarRequest> resultData = calendars.stream().map(CalendarRequest::new).collect(Collectors.toList());
        return new ReturnTypeV1<>(resultData);
    }

    /**
     * 스케쥴 상세보기
     * */
    @GetMapping("/schedule/{scheduleId}")
    public ReturnTypeV1<CalendarResponse> getSchedule(@PathVariable("scheduleId") Long scheduleId, @Login Long memberId){
        if(memberId == null) throw new IllegalStateException("로그인 정보가 존재하지 않습니다");
        Calendar schedule = calendarService.findById(scheduleId);
        if(!memberId.equals(schedule.getMember().getId())) throw new IllegalStateException("권한이 없습니다");
        return new ReturnTypeV1<>(new CalendarResponse(schedule));
    }

    /**
     * 일정 추가
     * */
    @PostMapping("/schedule")
    public ReturnTypeV1<Long> addSchedule(@RequestBody CalendarRequest calendarRequest, @Login Long memberId){
        if(memberId == null) throw new IllegalStateException("로그인 정보가 존재하지 않습니다");
        Long calendarId = calendarService.addSchedule(memberId,
                calendarRequest.getStartDate(), calendarRequest.getEndDate(),
                calendarRequest.getTitle(), calendarRequest.getMemo(), calendarRequest.getColor());

        return new ReturnTypeV1<>(calendarId);
    }

    /**
     * 일정 수정
     * */
    @PutMapping("/schedule/{scheduleId}")
    public ReturnTypeV1<CalendarResponse> editSchedule(@Login Long memberId, @PathVariable("scheduleId") Long scheduleId, @RequestBody CalendarRequest calendarRequest){
        if(memberId == null) throw new IllegalStateException("해당 일정을 찾을 수 없습니다");
        Calendar calendar = calendarService.updateSchedule(scheduleId, memberId,
                calendarRequest.getStartDate(), calendarRequest.getEndDate(),
                calendarRequest.getTitle(), calendarRequest.getMemo(), calendarRequest.getColor());
        return new ReturnTypeV1<>(new CalendarResponse(calendar));
    }

    /**
     * 일정 삭제
     * */
    @DeleteMapping("/schedule/{scheduleId}")
    public void deleteSchedule(@Login Long memberId, @PathVariable("scheduleId") Long scheduleId){
        if(memberId == null) throw new IllegalStateException("해당 일정을 찾을 수 없습니다");
        calendarService.deleteSchedule(scheduleId,memberId);
    }

    @Data
    @NoArgsConstructor
    private static class CalendarRequest{
        @NotBlank
        @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
        private LocalDate startDate;
        @NotBlank
        @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
        private LocalDate endDate;
        private String title;
        private String memo;
        private String color;

        public CalendarRequest(Calendar calendar) {
            this.startDate = calendar.getStartDate();
            this.endDate = calendar.getEndDate();
            this.title = calendar.getTitle();
            this.memo = calendar.getMemo();
            this.color = calendar.getTitle();
        }
    }
    @Data
    private static class CalendarResponse{
        private Long id;
        private LocalDate startDate;
        private LocalDate endDate;
        private String title;
        private String memo;
        private String color;

        public CalendarResponse(Calendar calendar) {
            this.id = calendar.getId();
            this.startDate = calendar.getStartDate();
            this.endDate = calendar.getEndDate();
            this.title = calendar.getTitle();
            this.memo = calendar.getMemo();
            this.color = calendar.getColor();
        }
    }
}
