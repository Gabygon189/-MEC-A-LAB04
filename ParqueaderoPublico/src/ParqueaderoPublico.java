import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;

public class ParqueaderoPublico {

    private static final int numCarros = 1;
    private static final List<Carro> Carros = new ArrayList<>();
    private static final Stack<Carro> motosBicicletas = new Stack<>();
    private static final Stack<Carro> carros = new Stack<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("Bienvenido a nuestro parqueadero. Elija una opcion:");
            System.out.println("1. Ingreso de vehiculo");
            System.out.println("2. Visualizar tabla actualizada con la informacion ingresada e incluya el valor a pagar");
            System.out.println("3. Visualizar en una lista los vehiculos de 2 ruedas e incluir el valor a pagar en total");
            System.out.println("4. Visualizar en una lista los carros de 4 ruedas e incluir el valor a pagar en total");
            System.out.println("5. Cantidad de vehiculos en parqueadero y valor total a pagar");
            System.out.println("6. Eliminar algun vehiculo");
            System.out.println("7. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine(); 
            switch(opcion) {
                case 'a':
                    ingresarVehiculo(scanner);
                    break;
                case 'b':
                    visualizarTablaActualizada();
                    break;
                case 'c':
                    visualizarListaVehiculos(motosBicicletas);
                    break;
                case 'd':
                    visualizarListaVehiculos(carros);
                    break;
                case 'e':
                    cantidadVehiculosParqueadero();
                    break;
                case 'f':
                    eliminarVehiculo(scanner);
                    break;
                case 'g':
                    System.out.println("Gracias por visitar nuestro parqueadero.");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Por favor seleccione una opcion valida.");
            }
        } while('g' != opcion);
    }
    
    private static void ingresarVehiculo(Scanner scanner) {
        System.out.println("Ingrese la placa del vehiculo:");
        String placa = scanner.nextLine();
        System.out.println("Ingrese el tipo de vehiculo en el que se transporta (1- bicicleta, 2- ciclomotor, 3- motocicleta, 4- carro):");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Ingrese la hora de su ingreso al parqueadero(formato 24 horas):");
        int hora = scanner.nextInt();
        scanner.nextLine(); 
        
        Carro carro = new Carro(numCarro, placa, tipo, hora);
        carro.add(carro);
        numCarro++;
        
        switch(tipo) {
            case 1:
            case 2:
                motosBicicletas.push(carro);
                break;
            case 3:
                motosBicicletas.push(carro);
                break;
            case 4:
                carros.push(carro);
                break;
            default:
                System.out.println("El tipo de vehiculo invalido. No se pudo agregar a la lista.");
    }
    
    System.out.println("El vehiculo ha sido ingresado exitosamente al parqueadero.");
}

private static void visualizarTablaActualizada() {
    System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "Carro", "Placa", "Tipo", "Hora de ingreso", "Valor a pagar");
    for(Carro carro : carros) {
        System.out.printf("%-15d%-15s%-15s%-15d%-15d\n", carro.getNumCarro(), carro.getPlaca(), 
                carro.getTipoVehiculo(), carro.getHoraIngreso(), carro.getValorAPagar());
    }
}

private static void visualizarListaVehiculos(Stack<Carro> lista) {
    int valorTotalAPagar = 0;
    System.out.printf("%-15s%-15s%-15s\n", "Carro", "Placa", "Valor a pagar");
    for(Carro carro : lista) {
        System.out.printf("%-15d%-15s%-15d\n", carro.getNumVehiculo(), carro.getPlaca(), carro.getValorAPagar());
        valorTotalAPagar += carro.getValorAPagar();
    }
    System.out.printf("Valor total: %d\n", valorTotalAPagar);
}

    private static void cantidadVehiculosParqueadero() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void eliminarVehiculo(Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void visualizarListaVehiculos(Stack<Carro> carros) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



