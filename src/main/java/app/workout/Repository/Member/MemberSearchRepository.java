package app.workout.Repository.Member;

import app.workout.Entity.Member.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberSearchRepository {

    List<Member> findSearchByName(String name, Pageable pageable);
    List<Member> findSearchByName(String name);
    List<Member> findSearchByNickname(String nickname, Pageable pageable);
    List<Member> findSearchByNickname(String nickname);
}
