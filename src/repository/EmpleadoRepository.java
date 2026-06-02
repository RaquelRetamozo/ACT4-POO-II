package repository;
import model.Empleado;
import java.util.List;

public interface EmpleadoRepository {
    void agregar(Empleado e);
    Empleado buscarPorId(int id);
    List<Empleado> listarTodos();
    void actualizar(Empleado e);
    void eliminar(int id);
}
