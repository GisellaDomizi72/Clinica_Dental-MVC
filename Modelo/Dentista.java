package Modelo;

public class Dentista {
   private int id_dentista;
   private String nombre;
   private String apellido;
   private String telefono;
   private String email;
   private String foto;
   private String exp;
   private int id_usuario;
   
   public Dentista(){
   }
   
   public Dentista(int id_dentista, String nombre, String apellido, String telefono, String email, String foto, String exp, int id_usuario){
       this.id_dentista = id_dentista;
       this.nombre = nombre;
       this.apellido = apellido;
       this.telefono = telefono;
       this.email = email;
       this.foto = foto;
       this.exp = exp;
       this.id_usuario = id_usuario;
   }

   //Setters
    public void setId_dentista(int id_dentista) {
        this.id_dentista = id_dentista;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public void setExp(String exp) {
        this.exp = exp;
    }

    //Getters
    public int getId_dentista() {
        return id_dentista;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getEmail() {
        return email;
    }
    public String getFoto() {
        return foto;
    }
    public String getExp() {
        return exp;
    }
    public int getId_usuario() {
        return id_usuario;
    }
}
