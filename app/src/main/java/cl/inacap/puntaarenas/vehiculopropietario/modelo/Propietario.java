package cl.inacap.puntaarenas.vehiculopropietario.modelo;

public class Propietario {
    private String rut;
    private String nombre;
    private String celular;

    public Propietario(String rut, String nombre, String celular) {
        this.rut = rut;
        this.nombre = nombre;
        this.celular = celular;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "[" +
                "rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", celular='" + celular + '\'' +
                ']';
    }
}
