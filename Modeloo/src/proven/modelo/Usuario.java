package proven.modelo;

public class Usuario {

    private long id_usuario;
    private String dni;
    private String nombre;
    private String apellidos;
    private String password;
    private String imagen;
    private String tipo_Usuario;
    private String correo;
    private int activo;

    //Constructor
    public Usuario(long id_usuario, String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo, int activo) {
        this.id_usuario = id_usuario;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.imagen = imagen;
        this.tipo_Usuario = tipo_Usuario;
        this.correo = correo;
        this.activo = activo;
    }

    public Usuario(long id_usuario, String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo) {
        this.id_usuario = id_usuario;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.imagen = imagen;
        this.tipo_Usuario = tipo_Usuario;
        this.correo = correo;
    }

    public Usuario(String dni, String password) {
        this.dni = dni;
        this.password = password;
    }

    public Usuario(String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.imagen = imagen;
        this.tipo_Usuario = tipo_Usuario;
        this.correo = correo;
    }

    public Usuario(long id_usuario, String tipo_Usuario) {
        this.id_usuario = id_usuario;
        this.tipo_Usuario = tipo_Usuario;
    }
    
    public Usuario(String dni) {
        this.dni = dni;
    }

    public Usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Usuario() {

    }
    //--------------------------------------------------------------------------

    //Getters
    public long getIdUsuario() {
        return this.id_usuario;
    }

    public String getDni() {
        return this.dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getPassword() {
        return this.password;
    }

    public String getImagen() {
        return this.imagen;
    }

    public String getTipoUsuario() {
        return this.tipo_Usuario;
    }

    public String getCorreo() {
        return this.correo;
    }

    public int getActivo() {
        return activo;
    }

    //Setters
    public void setIdUsuario(long idUsuario) {
        this.id_usuario = idUsuario;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setTipoUsiario(String tipo) {
        this.tipo_Usuario = tipo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    //toString
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Usuario [");
        sb.append(" id_usuario = " + id_usuario);
        sb.append(", dni = " + dni);
        sb.append(", nombre = " + nombre);
        sb.append(", apellidos = " + apellidos);
        sb.append(", password = " + password);
        sb.append(", imagen = " + imagen);
        sb.append(", tipo_Usuario = " + tipo_Usuario);
        sb.append(", correo = " + correo);
        sb.append(", activo = " + activo);
        sb.append(" ]");

        return sb.toString();
    }

}
