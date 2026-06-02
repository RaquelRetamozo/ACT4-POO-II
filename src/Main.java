import model.Empleado;
import repository.EmpleadoRepository;
import repository.EmpleadoRepositoryArchivo;
import repository.EmpleadoRepositoryMySQL;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmpleadoRepository repo;

        // Selección de fuente de datos
        System.out.println("=== SISTEMA DE GESTIÓN DE EMPLEADOS ===");
        System.out.println("Seleccione la fuente de almacenamiento:");
        System.out.println("1. MySQL");
        System.out.println("2. Archivo de texto");
        System.out.print("Opción: ");
        int fuente = sc.nextInt();

        if (fuente == 1) {
            repo = new EmpleadoRepositoryMySQL();
            System.out.println("Usando MySQL.");
        } else {
            repo = new EmpleadoRepositoryArchivo();
            System.out.println("Usando archivo de texto.");
        }

        // Menú principal
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Buscar empleado por ID");
            System.out.println("3. Listar todos");
            System.out.println("4. Actualizar empleado");
            System.out.println("5. Eliminar empleado");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = sc.nextInt(); sc.nextLine();
                    System.out.print("Departamento: ");
                    String depto = sc.nextLine();
                    repo.agregar(new Empleado(depto, edad, id, nombre));
                    break;

                case 2:
                    System.out.print("ID a buscar: ");
                    int idBuscar = sc.nextInt();
                    Empleado encontrado = repo.buscarPorId(idBuscar);
                    if (encontrado != null) {
                        System.out.println("Encontrado: " + encontrado);
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;

                case 3:
                    List<Empleado> lista = repo.listarTodos();
                    if (lista.isEmpty()) {
                        System.out.println("No hay empleados registrados.");
                    } else {
                        lista.forEach(System.out::println);
                    }
                    break;

                case 4:
                    System.out.print("ID a actualizar: ");
                    int idAct = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nueva edad: ");
                    int nuevaEdad = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo departamento: ");
                    String nuevoDepto = sc.nextLine();
                    repo.actualizar(new Empleado(nuevoDepto, nuevaEdad, idAct, nuevoNombre));
                    break;

                case 5:
                    System.out.print("ID a eliminar: ");
                    int idElim = sc.nextInt();
                    repo.eliminar(idElim);
                    break;

                case 6:
                    System.out.println("Hasta luego.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        sc.close();
    }
}