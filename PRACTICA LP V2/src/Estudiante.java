public class Estudiante {
    private int id;
    private String first_name;
    private String last_name;
    private String gender;
    private String career_aspiration;
    private double math_score;

    public Estudiante(int id, String firstName, String lastName, String gender, String careerAspiration, double mathScore) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.gender = gender;
        this.career_aspiration = careerAspiration;
        this.math_score = mathScore;
    }

    // Getters y setters aquí

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_Name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", career_aspiration='" + career_aspiration + '\'' +
                ", math_score=" + math_score +
                '}';
    }
    public String getcareer_aspiration() {
        return career_aspiration;
    }

    public String getgender() {
        return gender;
    }

    public double getmath_score() {
        return math_score;
    }

    public String getlast_Name() {
        return last_name;
    }

    public String getfirst_name() {
        return first_name;
    }

}
