package app.workout;

import app.workout.Entity.Member.Member;
import app.workout.Entity.Workout.Routine;
import app.workout.Entity.Workout.Volume;
import app.workout.Entity.Workout.Workout;
import app.workout.Repository.Member.MemberRepository;
import app.workout.Repository.Routine.RoutineRepository;
import app.workout.Repository.Workout.WorkoutRepository;
import app.workout.Service.Member.ApiClientService;
import app.workout.Service.Member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class WorkoutApplication {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberService memberService;

	@Autowired
	private WorkoutRepository workoutRepository;
	@Autowired
	private RoutineRepository routineRepository;

	@Autowired
	private ApiClientService apiClientService;
	public static void main(String[] args) {

		SpringApplication.run(WorkoutApplication.class, args);
	}

	@Bean
	public void initData(){
		Long join = memberService.join("testA", "testtesttest!", "TesterA", "testerA");
		memberService.join("testB","testtesttest!","testerB","testerB");

		Optional<Member> byId = memberRepository.findById(join);

		apiClientService.issueApiKey(join);


		Workout workoutA = new Workout("workoutA",null,null);
		Workout workoutB = new Workout("workoutB",null,null);
		Workout workoutC = new Workout("workoutC",null,null);

		workoutRepository.save(workoutA);
		workoutRepository.save(workoutB);
		workoutRepository.save(workoutC);


		Volume volume = Volume.createVolume(20, 5, workoutA);
		Volume volume1 = Volume.createVolume(20, 5, workoutB);
		Routine testA = Routine.createRoutine("testA", null, true, byId.get() , volume,volume1);
		routineRepository.save(testA);

		Volume volume3 = Volume.createVolume(50, 3, workoutA);
		Volume volume4 = Volume.createVolume(50, 3, workoutB);

		Routine testB = Routine.createRoutine("testB", null, true, byId.get() , volume3,volume4);
		routineRepository.save(testB);
	}

}
