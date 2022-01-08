package app.workout;

import app.workout.Entity.Member.Member;
import app.workout.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WorkoutApplication {

	@Autowired
	private MemberRepository memberRepository;

	public static void main(String[] args) {

		SpringApplication.run(WorkoutApplication.class, args);
	}

	@Bean
	public void initData(){
		Member member = new Member("testA","test!","testerA","testerA");
		Member member2 = new Member("testB","test!","testerB","testerB");
		memberRepository.save(member);
		memberRepository.save(member2);
	}

}
