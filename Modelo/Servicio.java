package Modelo;
public class Servicio {
    private int id_servicio;
    private String nombre_s;
    private int precio_s;
    private String descripcion_s;
    private int id_dentista;

    public Servicio() {
    }

    public Servicio(int id_servicio, String nombre_s, int precio_s, String descripcion_s, int id_dentista) {
        this.id_servicio = id_servicio;
        this.nombre_s = nombre_s;
        this.precio_s = precio_s;
        this.descripcion_s = descripcion_s;
        this.id_dentista = id_dentista;
    }

    //Setters
    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }
    public void setNombre(String nombre) {
        this.nombre_s = nombre;
    }
    public void setPrecio(int precio) {
        this.precio_s = precio;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion_s = descripcion;
    }
    public void setId_dentista(int id_dentista) {
        this.id_dentista = id_dentista;
    }
    
    //Getters
    public int getId_servicio() {
        return id_servicio;
    }
    public String getNombre() {
        return nombre_s;
    }
    public int getPrecio() {
        return precio_s;
    }
    public String getDescripcion() {
        return descripcion_s;
    }
    public int getId_dentista() {
        return id_dentista;
    }   
}
