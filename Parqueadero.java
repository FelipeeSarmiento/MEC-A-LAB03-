import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;

public class Parqueadero {

    private static int numVehiculo = 1;
    private static final List<Vehiculo> vehiculos = new ArrayList<>();
    private static final Stack<Vehiculo> motosBicicletas = new Stack<>();
    private static final Stack<Vehiculo> carros = new Stack<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("Bienvenido al parqueadero. Seleccione una opción:");
            System.out.println("1. Ingreso de vehículo");
            System.out.println("2. Visualizar tabla actualizada con la información ingresada e incluya el valor a pagar");
            System.out.println("3. Visualizar en una lista los vehículos de 2 ruedas e incluir el valor a pagar en total");
            System.out.println("4. Visualizar en una lista los vehículos de 4 ruedas e incluir el valor a pagar en total");
            System.out.println("5. Cantidad de vehículos en parqueadero y valor total a pagar");
            System.out.println("6. Eliminar algún vehículo");
            System.out.println("7. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea en blanco
            switch(opcion) {
                case 1:
                    ingresarVehiculo(scanner);
                    break;
                case 2:
                    visualizarTablaActualizada();
                    break;
                case 3:
                    visualizarListaVehiculos(motosBicicletas);
                    break;
                case 4:
                    visualizarListaVehiculos(carros);
                    break;
                case 5:
                    cantidadVehiculosParqueadero();
                    break;
                case 6:
                    eliminarVehiculo(scanner);
                    break;
                case 7:
                    System.out.println("Gracias por utilizar el parqueadero.");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor seleccione una opción válida.");
            }
        } while(opcion != 7);
    }
    
    private static void ingresarVehiculo(Scanner scanner) {
        System.out.println("Ingrese la placa del vehículo:");
        String placa = scanner.nextLine();
        System.out.println("Ingrese el tipo de vehículo (1 - bicicleta, 2 - ciclomotor, 3 - motocicleta, 4 - carro):");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea en blanco
        System.out.println("Ingrese la hora de ingreso (formato 24 horas):");
        int hora = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea en blanco
        
        Vehiculo vehiculo = new Vehiculo(numVehiculo, placa, tipo, hora);
        vehiculos.add(vehiculo);
        numVehiculo++;
        
        switch(tipo) {
            case 1:
            case 2:
                motosBicicletas.push(vehiculo);
                break;
            case 3:
                motosBicicletas.push(vehiculo);
                break;
            case 4:
                carros.push(vehiculo);
                break;
            default:
                System.out.println("Tipo de vehículo inválido. No se pudo agregar a la lista de vehículos.");
    }
    
    System.out.println("El vehículo ha sido ingresado exitosamente al parqueadero.");
}

private static void visualizarTablaActualizada() {
    System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "N° Vehículo", "Placa", "Tipo", "Hora de ingreso", "Valor a pagar");
    for(Vehiculo vehiculo : vehiculos) {
        System.out.printf("%-15d%-15s%-15s%-15d%-15d\n", vehiculo.getNumVehiculo(), vehiculo.getPlaca(), 
                vehiculo.getTipoVehiculo(), vehiculo.getHoraIngreso(), vehiculo.getValorAPagar());
    }
}

private static void visualizarListaVehiculos(Stack<Vehiculo> lista) {
    int valorTotalAPagar = 0;
    System.out.printf("%-15s%-15s%-15s\n", "N° Vehículo", "Placa", "Valor a pagar");
    for(Vehiculo vehiculo : lista) {
        System.out.printf("%-15d%-15s%-15d\n", vehiculo.getNumVehiculo(), vehiculo.getPlaca(), vehiculo.getValorAPagar());
        valorTotalAPagar += vehiculo.getValorAPagar();
    }
    System.out.printf("Valor total a pagar: %d\n", valorTotalAPagar);
}

private static void cantidadVehiculosParqueadero() {
    int numVehiculos = vehiculos.size();
    int valorTotalAPagar = 0;
    for(Vehiculo vehiculo : vehiculos) {
        valorTotalAPagar += vehiculo.getValorAPagar();
    }
    System.out.printf("Hay %d vehículos en el parqueadero.\n", numVehiculos);
    System.out.printf("Valor total a pagar: %d\n", valorTotalAPagar);
}

private static void eliminarVehiculo(Scanner scanner) {
    System.out.println("Ingrese el número de vehículo que desea eliminar:");
    int numVehiculo = scanner.nextInt();
    scanner.nextLine(); // Consumir la línea en blanco
    boolean eliminado = false;
    for(int i = 0; i < vehiculos.size(); i++) {
        if(vehiculos.get(i).getNumVehiculo() == numVehiculo) {
            Vehiculo vehiculo = vehiculos.remove(i);
            eliminado = true;
            switch(vehiculo.getTipo()) {
                case 1:
                case 2:
                    motosBicicletas.remove(vehiculo);
                    break;
                case 3:
                    motosBicicletas.remove(vehiculo);
                    break;
                case 4:
                    carros.remove(vehiculo);
                    break;
                default:
                    break;
            }
            System.out.printf("El vehículo con número %d y placa %s ha sido eliminado exitosamente del parqueadero.\n", 
                    vehiculo.getNumVehiculo(), vehiculo.getPlaca());
            break;
        }
    }
    if(!eliminado) {
        System.out.println("El número de vehículo ingresado no corresponde a ningún vehículo en el parqueadero.");
    }
}

private static class Vehiculo {

        private final int numVehiculo;
        private final String placa;
        private final int tipo;
        private final int horaIngreso;
    
    public Vehiculo(int numVehiculo, String placa, int tipo, int horaIngreso) {
        this.numVehiculo = numVehiculo;
this.placa = placa;
this.tipo = tipo;
this.horaIngreso = horaIngreso;
calcularValorAPagar();
}
public int getNumVehiculo() {
        return numVehiculo;
    }
    
    public String getPlaca() {
        return placa;
    }
    
    public int getTipo() {
        return tipo;
    }
    
    public String getTipoVehiculo() {
        switch(tipo) {
            case 1:
                return "Bicicleta";
            case 2:
                return "Ciclomotor";
            case 3:
                return "Motocicleta";
            case 4:
                return "Carro";
            default:
                return "";
        }
    }
    
    public int getHoraIngreso() {
        return horaIngreso;
    }
    
    public int getValorAPagar() {
            int valorAPagar = 0;
        return valorAPagar;
    }
    
    private void calcularValorAPagar() {
        switch(tipo) {
            case 1:
            case 2:
                break;

            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }
    }
}