package app.workout.Entity.Workout.Eunm;

public enum ExercisePart {
    // 상체
    CHEST("가슴"), BACK("등"), SHOULDERS("어깨"),
    // 팔
    BICEPS("이두"), TRICEPS("삼두"),
    // 하체
    THIGHS("대퇴"), HAMSTRINGS("햄스트링"), CALVES("종아리");

    private String value;

    ExercisePart(String value){
        this.value = value;
    }
    public String toKorean(){
        return value;
    }

}
