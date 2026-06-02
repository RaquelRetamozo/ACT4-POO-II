public class Empleado {

    //Atributos
    private int id;
    private String nombre;
    private int edad;
    private String departamento;

    //Constructores
    public Empleado(String departamento, int edad, int id, String nombre) {
        this.departamento = departamento;
        this.edad = edad;
        this.id = id;
        this.nombre = nombre;
    }
    public Empleado() {
    }

    //Getters y Setters
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //toString
    @Override
    public String toString() {
        return id + "|" + nombre + "|" + edad + "|" + departamento;
    }
}