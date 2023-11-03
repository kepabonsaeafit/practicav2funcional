import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Estudiante> estudiantes = leerDatosDesdeArchivoCSV("C:\\Users\\kepab\\OneDrive\\Escritorio\\estudiantes notas.csv");

        // Realiza las operaciones requeridas con los estudiantes
        aspirantesPorCarrera(estudiantes);
        totalMujeresPorCarrera(estudiantes);
        totalHombresPorCarrera(estudiantes);
        estudianteConMejorPuntajePorCarrera(estudiantes);
        estudianteConMejorPuntajeGeneral(estudiantes);
        puntajePromedioPorCarrera(estudiantes);
    }

    public static List<Estudiante> leerDatosDesdeArchivoCSV(String archivo) throws IOException {
        return Files.lines(Path.of(archivo))
                .skip(1)  // Saltar la primera línea (encabezado)
                .map(line -> {
                    String[] partes = line.split(",");
                    int id = Integer.parseInt(partes[0]);
                    String first_name = partes[1];
                    String last_name = partes[2];
                    String gender = partes[4];
                    String career_aspiration = partes[8];
                    double mathScore = Double.parseDouble(partes[9]);
                    return new Estudiante(id, first_name, last_name, gender, career_aspiration, mathScore);
                })
                .collect(Collectors.toList());
    }

    public static void aspirantesPorCarrera(List<Estudiante> estudiantes) {
        Map<String, Long> aspirantesPorCarrera = estudiantes.stream()
                .collect(Collectors.groupingBy(Estudiante::getcareer_aspiration, Collectors.counting()));

        System.out.println("Aspirantes por carrera:");
        aspirantesPorCarrera.forEach((carrera, total) -> System.out.println(carrera + ": " + total));
    }

    public static void totalMujeresPorCarrera(List<Estudiante> estudiantes) {
        Predicate<Estudiante> mujeres = estudiante -> "female".equalsIgnoreCase(estudiante.getgender());

        Map<String, Long> totalMujeresPorCarrera = estudiantes.stream()
                .filter(mujeres)
                .collect(Collectors.groupingBy(Estudiante::getcareer_aspiration, Collectors.counting()));

        System.out.println("\nTotal de mujeres por carrera:");
        totalMujeresPorCarrera.forEach((carrera, total) -> System.out.println(carrera + ": " + total));
    }

    public static void totalHombresPorCarrera(List<Estudiante> estudiantes) {
        Predicate<Estudiante> hombres = estudiante -> "male".equalsIgnoreCase(estudiante.getgender());

        Map<String, Long> totalHombresPorCarrera = estudiantes.stream()
                .filter(hombres)
                .collect(Collectors.groupingBy(Estudiante::getcareer_aspiration, Collectors.counting()));

        System.out.println("\nTotal de hombres por carrera:");
        totalHombresPorCarrera.forEach((carrera, total) -> System.out.println(carrera + ": " + total));
    }

    public static void estudianteConMejorPuntajePorCarrera(List<Estudiante> estudiantes) {
        Map<String, Estudiante> estudianteConMejorPuntajePorCarrera = estudiantes.stream()
                .collect(Collectors.toMap(Estudiante::getcareer_aspiration, student -> student,
                        (existing, replacement) -> existing.getmath_score() > replacement.getmath_score() ? existing : replacement));

        System.out.println("\nEstudiante con el puntaje más alto (math_score) por carrera:");
        estudianteConMejorPuntajePorCarrera.forEach((carrera, student) ->
                System.out.println(carrera + ": " + student.getfirst_name() + " " + student.getlast_Name() + " - Puntaje: " + student.getmath_score()));
    }

    public static void estudianteConMejorPuntajeGeneral(List<Estudiante> estudiantes) {
        Estudiante estudianteConMejorPuntajeGeneral = estudiantes.stream()
                .max(Comparator.comparing(Estudiante::getmath_score))
                .orElse(null);

        System.out.println("\nEstudiante con el puntaje más alto (math_score) de todos:");
        if (estudianteConMejorPuntajeGeneral != null) {
            System.out.println(estudianteConMejorPuntajeGeneral.getfirst_name() + " " + estudianteConMejorPuntajeGeneral.getlast_Name() + " - Puntaje: " + estudianteConMejorPuntajeGeneral.getmath_score());
        } else {
            System.out.println("No se encontró ningún estudiante.");
        }
    }

    public static void puntajePromedioPorCarrera(List<Estudiante> estudiantes) {
        Map<String, Double> puntajePromedioPorCarrera = estudiantes.stream()
                .collect(Collectors.groupingBy(Estudiante::getcareer_aspiration, Collectors.averagingDouble(Estudiante::getmath_score)));

        System.out.println("\nPuntaje Promedio (math_score) por carrera:");
        puntajePromedioPorCarrera.forEach((carrera, promedio) -> System.out.println(carrera + ": " + promedio));
    }
}
