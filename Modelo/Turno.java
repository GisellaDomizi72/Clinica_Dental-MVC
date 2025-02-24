package Modelo;

public class Turno {
    private int id_turno;
    private String hora;
    private String fecha;
    private String nombre_paciente;  // Nuevo atributo para el nombre completo del paciente
    private String nombre_servicio;  // Nuevo atributo para el nombre del servicio
    
    // Constructor vacío
    public Turno(){}

    // Constructor con todos los atributos
    public Turno(int id_turno, String hora, String fecha, String nombre_paciente, String nombre_servicio) {
        this.id_turno = id_turno;
        this.hora = hora;
        this.fecha = fecha;
        this.nombre_paciente = nombre_paciente;
        this.nombre_servicio = nombre_servicio;
    }
    
    // Setters
    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setNombre_paciente(String nombre_paciente) {
        this.nombre_paciente = nombre_paciente;
    }
    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    // Getters
    public int getId_turno() {
        return id_turno;
    }
    public String getHora() {
        return hora;
    }
    public String getFecha() {
        return fecha;
    }
    public String getNombre_paciente() {
        return nombre_paciente;
    }
    public String getNombre_servicio() {
        return nombre_servicio;
    }
}

