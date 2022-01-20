package app.workout.Service.Calendar;

import app.workout.Entity.Calendar.Calendar;
import app.workout.Entity.Member.Member;
import app.workout.Messages.ErrorMessages;
import app.workout.Repository.CalendarRepository;
import app.workout.Repository.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final MemberRepository memberRepository;

    /**
     * 특정 스케쥴 찾기
     * */
    public Calendar findById(Long scheduleId){
        return calendarRepository.findById(scheduleId).orElseThrow(()->{
            throw new IllegalStateException("찾을 수 없는 일정입니다");
        });


    }


    /**
     * 범위의 스케쥴 찾기
     * */
    public List<Calendar> rangeSchedule(Long memberId,LocalDate start, LocalDate end){
       return calendarRepository.findRangeByMember(memberId, start, end);
    }

    /**
     * 페이징있는 스케쥴 전부 가져오기
     * */
    public List<Calendar> findAll(Pageable pageable){
       return calendarRepository.findAll(pageable).get().collect(Collectors.toList());
    }

    /**
     * 캘린더에 스케쥴 추가
     * */
    @Transactional
    public Long addSchedule(Long memberId , LocalDate start, LocalDate end, String title, String memo, String color){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalStateException(ErrorMessages.NOT_FOUND_USER);
        });
        Calendar calendar = new Calendar();
        calendar.setDate(start,end);
        calendar.setContent(title,memo,color);
        calendar.setMember(member);
        calendarRepository.save(calendar);
        return calendar.getId();
    }

    /**
     * 캘린더 스케쥴 수정
     * */
    @Transactional
    public Calendar updateSchedule(Long scheduleId, Long memberId, LocalDate start, LocalDate end, String title, String memo, String color){
        Calendar calendar = calendarRepository.findById(scheduleId).orElseThrow(() -> {
            throw new IllegalStateException(ErrorMessages.NOT_FOUND_SCHEDULE);
        });
        if(!Objects.equals(scheduleId, memberId)){
            throw new IllegalStateException(ErrorMessages.NO_PERMISSION);
        }
        calendar.setDate(start,end);
        calendar.setContent(title,memo,color);
        return calendar;
    }
    @Transactional
    public Calendar updateSchedule(Long scheduleId, LocalDate start, LocalDate end, String title, String memo, String color){
        Calendar calendar = calendarRepository.findById(scheduleId).orElseThrow(() -> {
            throw new IllegalStateException(ErrorMessages.NOT_FOUND_SCHEDULE);
        });
        calendar.setDate(start,end);
        calendar.setContent(title,memo,color);
        return calendar;
    }

    /**
     * 캘린더 스케쥴 삭제
     * */
    @Transactional
    public void deleteSchedule(Long scheduleId){
        calendarRepository.deleteById(scheduleId);
    }
    /**
     * 캘린더 스케쥴 삭제
     * */
    @Transactional
    public void deleteSchedule(Long scheduleId, Long memberId){
        Calendar calendar = findById(scheduleId);
        if(!Objects.equals(calendar.getMember().getId(), memberId)){
            throw new IllegalStateException(ErrorMessages.NO_PERMISSION);
        }
        calendarRepository.delete(calendar);
    }
}
