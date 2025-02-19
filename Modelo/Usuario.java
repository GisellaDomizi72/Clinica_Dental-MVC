package Modelo;
public class Usuario {
    private int id_usuario;
    private String nombre;
    private String contrasena;
    private int rol;
    
    public Usuario(){
    }
    
    public Usuario(int id_usuario,String nombre, String contrasena, int rol){
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.rol =rol;
    }
       
    //Setters
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    public void setRol(int rol) {
        this.rol = rol;
    }
    
    //Getters
    public int getId_usuario() {
        return id_usuario;
    }
    public String getNombre(){
        return nombre;
    }
    public String getContrasena(){
        return contrasena;
    }
    public int getRol() {
        return rol;
    }
    
}
