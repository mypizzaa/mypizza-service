package proven.modelo;

public class Cliente extends Usuario {

    private long id_cliente;
    private String telefono;
    private String primeraDireccion;
    private String segundaDireccion;    
    private String poblacion;
    private String codigo_postal;

    //constructor

    public Cliente(long id_cliente, String telefono, String primeraDireccion, String segundaDireccion, String poblacion, String codigo_postal, long id_usuario, String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo, int activo) {
        super(id_usuario, dni, nombre, apellidos, password, imagen, tipo_Usuario, correo, activo);
        this.id_cliente = id_cliente;
        this.telefono = telefono;
        this.primeraDireccion = primeraDireccion;
        this.segundaDireccion = segundaDireccion;
        this.poblacion = poblacion;
        this.codigo_postal = codigo_postal;
    }

    
    
    public Cliente(long id_usuario, String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo, String primeraDireccion, String segundaDireccion, String telefono, String poblacion, String codigo_postal) {
        super(id_usuario, dni, nombre, apellidos, password, imagen, tipo_Usuario, correo);
        this.telefono = telefono;
        this.primeraDireccion = primeraDireccion;
        this.segundaDireccion = segundaDireccion;
        this.poblacion = poblacion;
        this.codigo_postal = codigo_postal;
    }

    

    public Cliente(String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo,String primeraDireccion, String segundaDireccion, String telefono, String poblacion, String codigo_postal) {
        super(dni, nombre, apellidos, password, imagen, tipo_Usuario, correo);
        this.primeraDireccion = primeraDireccion;
        this.segundaDireccion = segundaDireccion;
        this.telefono = telefono;
        this.poblacion = poblacion;
        this.codigo_postal = codigo_postal;
    }

    public Cliente(String dni, String password) {
        super(dni, password);
    }

    public Cliente(String dni) {
        super(dni);
    }

    //--------------------------------------------------------------------------
    //getters
    public long getIdCliente() {
        return this.id_cliente;
    }

    public String getPrimeraDireccion() {
        return this.primeraDireccion;
    }

    public String getSegundaDireccion() {
        return this.segundaDireccion;
    }

    //setters
    public void setIdCliente(long idcliente) {
        this.id_cliente = idcliente;
    }

    public void setPrimeraDireccion(String direccion) {
        this.primeraDireccion = direccion;
    }

    public void setSegundaDireccion(String direccion) {
        this.segundaDireccion = direccion;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id_cliente=" + id_cliente + ", telefono=" + telefono + ", primeraDireccion=" + primeraDireccion + ", segundaDireccion=" + segundaDireccion + ", poblacion=" + poblacion + ", codigo_postal=" + codigo_postal + "}"+super.toString();
    }
    
    
}
