package app.workout.Entity.Workout.Eunm;

public enum ExerciseType {
    WEIGHT_TRAINING("무산소"), CARDIO("유산소");

    private String value;

    ExerciseType(String value){
        this.value = value;
    }

    public String toKorean(){
        return value;
    }
}
