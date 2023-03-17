import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EPS {

    static class Paciente {
        String nombre;
        int edad;
        String afiliacion;
        String condicionEspecial;

        public Paciente(String nombre, int edad, String afiliacion, String condicionEspecial) {
            this.nombre = nombre;
            this.edad = edad;
            this.afiliacion = afiliacion;
            this.condicionEspecial = condicionEspecial;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    public static void main(String[] args) {

        ConcurrentLinkedQueue<Paciente> espera = new ConcurrentLinkedQueue<>();
        int turno = 1;

        Thread hilo = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                Paciente paciente = espera.poll();
                if (paciente != null) {
                    System.out.println("Turno: " + turno + " - Paciente: " + paciente);
                    turno++;
                }
            }
        });
        hilo.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese los datos del paciente:");
            System.out.print("Nombre y apellidos: ");
            String nombre = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Afiliación (POS o PC): ");
            String afiliacion = scanner.nextLine();
            System.out.print("Condición especial (embarazo o limitación motriz): ");
            String condicionEspecial = scanner.nextLine();

            Paciente paciente = new Paciente(nombre, edad, afiliacion, condicionEspecial);

            if (paciente.edad >= 60 || paciente.edad < 12) {
                espera.add(paciente);
                System.out.println("Turno: " + turno + " - Paciente: " + paciente);
                turno++;
            } else if (paciente.condicionEspecial.equalsIgnoreCase("embarazo")) {
                espera.add(paciente);
                System.out.println("Turno: " + turno + " - Paciente: " + paciente);
                turno++;
            } else if (paciente.condicionEspecial.equalsIgnoreCase("limitación motriz")) {
                espera.add(paciente);
                System.out.println("Turno: " + turno + " - Paciente: " + paciente);
                turno++;
            } else if (paciente.afiliacion.equalsIgnoreCase("PC") || paciente.afiliacion.equalsIgnoreCase("prepagada")) {
                espera.add(paciente);
                System.out.println("Turno: " + turno + " - Paciente: " + paciente);
                turno++;
            } else {
                espera.add(paciente);
                System.out.println("Turno: " + turno + " - Paciente: " + paciente);
                turno++;
            }
        }
    }
}