package repository;
import model.Empleado;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepositoryArchivo implements EmpleadoRepository {

    private static final String ARCHIVO = "empleados.txt";

    private List<Empleado> leerTodos() {
        List<Empleado> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                Empleado e = new Empleado();
                e.setId(Integer.parseInt(partes[0]));
                e.setNombre(partes[1]);
                e.setEdad(Integer.parseInt(partes[2]));
                e.setDepartamento(partes[3]);
                lista.add(e);
            }
        } catch (IOException ex) {
            System.out.println("Archivo no encontrado, se creará uno nuevo.");
        }
        return lista;
    }

    private void escribirTodos(List<Empleado> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Empleado e : lista) {
                bw.write(e.toString());
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo: " + ex.getMessage());
        }
    }

    @Override
    public void agregar(Empleado e) {
        List<Empleado> lista = leerTodos();
        lista.add(e);
        escribirTodos(lista);
    }

    @Override
    public Empleado buscarPorId(int id) {
        for (Empleado e : leerTodos()) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    @Override
    public List<Empleado> listarTodos() {
        return leerTodos();
    }

    @Override
    public void actualizar(Empleado empleado) {
        List<Empleado> lista = leerTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == empleado.getId()) {
                lista.set(i, empleado);
                break;
            }
        }
        escribirTodos(lista);
    }

    @Override
    public void eliminar(int id) {
        List<Empleado> lista = leerTodos();
        lista.removeIf(e -> e.getId() == id);
        escribirTodos(lista);
    }
}