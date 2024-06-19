import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PromNotas {
    Scanner input = new Scanner(System.in);

    //instanciacion de hashmaps a usar:
    HashMap <String, Double> studentsGrades = new HashMap<>();

    //declaracion de cantidad de notas a promediar:
    final int cantidadNotas = 3;

    //proyeccion del menu para la eleccion del usuario:
    public void menu() {

        int option = 0;
        String mensaje = "\n            ####MENU DE OPCIONES####\n\n" +
                //el ingreso debe ser validado para que esté en un rango de 1-5
                "1. Ingreso notas de estudiantes.\n" +
                "2. Imprimir cantidad de estudiantes ingresados.\n" +
                "3. Imprimir la cantidad de notas ingresadas.\n" +
                "4. Cantidad de estudiantes que GANARON la materia.\n" +
                //pierden cuando su promedio es menor a 3.5
                "5. Cantidad de estudiantes que PERDIERON la materia.\n" +
                //cuando la nota final es mayor de 2.0
                "6. Cantidad de estudiantes que pueden recuperar.\n" +
                "7. Cantidad de estudiantes que pierden sin recuperar.\n" +
                "8. Promedio de las notas finales obtenidas.\n" +
                "9. Imprimir el listado de estudiantes y sus notas finales.\n" +
                "10. Salir\n";

        do {
            System.out.println(mensaje);
            System.out.println("Ingresa la opción: \n");
            option = input.nextInt();
            validacionMenu(option);
        } while (option != 10);

    }

    private void validacionMenu(int option) {
        switch (option) {
            case 1:
                addGrades();
                break;
            case 2:
                System.out.println("\nLa cantidad de estudiantes ingresados son: " + countStudents());
                break;
            case 3:
                System.out.println("\nLa cantidad de notas ingresadas son: " + countGrades());
                break;
            case 4:
                System.out.println("\nLa cantidad de estudiantes que GANARON la materia son: " + wonSubject());
                break;
            case 5:
                System.out.println("\nLa cantidad de estudiantes que PERDIERON la materia son: " + failedSubject());
                break;
            case 6:
                System.out.println("\nLa cantidad de estudiantes que PUEDEN recuperar es : " + canRecover());
                break;
            case 7:
                System.out.println("\nLa cantidad de estudiantes que pierden sin recuperar es : " + cannotRecover());
                break;
            case 8:
                int cantidadNotas = countGrades();
                System.out.println("\nEl promedio de las notas finales obtenidas es: " + getPromedioFinalGrades(cantidadNotas));
                break;
            case 9:
                printStudentsGrades();
                break;
            case 10:
                System.out.println("         SALIENDO DEL SISTEMA...");
                break;

        }
    }

    private double getPromedioFinalGrades(int cantidadNotas) {
        double sum = 0;
        for (Double counterValue : studentsGrades.values()) {
            sum += counterValue;
        }
        return sum/cantidadNotas;
    }

    private int cannotRecover() {
        int sum = 0;
        for (Double counterValue : studentsGrades.values()) {
            if (counterValue <= 2.0) {
                sum++;
            }
        }
        return sum;
    }

    private int canRecover() {
        int sum = 0;
        for (Double counterValue : studentsGrades.values()) {
            if (counterValue > 2.0 && counterValue < 3.5) {
                sum++;
            }
        }
        return sum;
    }

    private int failedSubject() {
        int sumFailers = 0;
        for (Double counterValue : studentsGrades.values()) {
            if (counterValue < 3.5) {
                sumFailers++;
            }
        }
        return sumFailers;
    }

    private int wonSubject() {
        int sumWinners = 0;
        for (Double counterValue : studentsGrades.values()) {
            if (counterValue >= 3.5) {
                sumWinners++;
            }
        }
        return sumWinners;
    }

    private int countGrades() {
        int sum = 0;
        for (double counterValue : studentsGrades.values()) {
            sum++;
        }
        return sum;
    }

    private int countStudents() {
        int sum = 0;
        for (String counterKey : studentsGrades.keySet()) {
            sum++;
        }
        return sum;
    }

    private void printStudentsGrades() {
        System.out.println("            ####DATOS INGRESADOS####");
        for (Map.Entry<String, Double> entry : studentsGrades.entrySet()){
            System.out.println("\nESTUDIANTE: " + entry.getKey() + "\nNOTA FINAL: " + entry.getValue());
        }
    }

    private void addGrades() {
        System.out.println("\nIngresa la cantidad de estudiantes a ingresar sus notas: ");
        int cantidadEstudiantes = input.nextInt();

        for (int i = 0; i < cantidadEstudiantes; i++) {
            //obteniendo el nombre del estudiante (key)
            System.out.println("\nIngresa el nombre del estudiante " + (i+1));
            String name = input.next();

            //obteniendo el promedio del estudiante (value) :
            double notaFinal = getPromedioNotas(cantidadNotas);

            //añadiendo las keys y values del hashmap segun los datos obtenidos:
            studentsGrades.put(name, notaFinal);
        }
    }

    private double getPromedioNotas(int cantidadNotas) {
        double sum = 0;
        for (int i = 0; i < cantidadNotas; i++) {
            System.out.println("Ingresa la nota " + (i+1));
            double nota = input.nextDouble();
            if (nota < 1 || nota > 5) {
                System.out.println("Error: Ingresa una nota en el rango 1-5\n");
                i--;
                continue;
            }
            sum += nota;
        }
        return sum/cantidadNotas;
    }

}