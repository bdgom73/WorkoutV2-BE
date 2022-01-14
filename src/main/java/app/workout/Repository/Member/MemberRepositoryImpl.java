package app.workout.Repository.Member;

import app.workout.Entity.Member.Member;
import app.workout.Entity.Member.QMember;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional
public class MemberRepositoryImpl implements MemberSearchRepository {

    private final EntityManager em;
    @Override
    public List<Member> findSearchByName(String name, Pageable pageable) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        QMember m = QMember.member;
        JPAQuery<Member> jpaQuery = query.select(m).from(m)
                .where(m.name.toUpperCase().contains(name.toUpperCase()))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        for(Sort.Order o : pageable.getSort()){
            Order direction = o.getDirection().isAscending() ? Order.ASC : Order.DESC;
            PathBuilder pathBuilder = new PathBuilder(m.getType(), m.getMetadata());
            jpaQuery.orderBy(new OrderSpecifier<>(direction, pathBuilder.get(o.getProperty())));
        }

        return jpaQuery.fetch();
    }

    @Override
    public List<Member> findSearchByName(String name) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        QMember member = QMember.member;
        return query.select(member).from(member)
                .where(member.name.toUpperCase().contains(name.toUpperCase()))
                .fetch();
    }

    @Override
    public List<Member> findSearchByNickname(String nickname, Pageable pageable) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        QMember member = QMember.member;
        return query.select(member).from(member)
                .where(member.nickname.toUpperCase().contains(nickname.toUpperCase()))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
    }

    @Override
    public List<Member> findSearchByNickname(String nickname) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        QMember member = QMember.member;
        return query.select(member).from(member)
                .where(member.nickname.toUpperCase().contains(nickname.toUpperCase()))
                .fetch();
    }
}
