package proven.modelo;

public class Cliente extends Usuario {

    private long id_cliente;
    private String primeraDireccion;
    private String segundaDireccion;
    private String telefono;

    //constructor
    public Cliente(long id_usuario, String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo, long id_cliente, String primeraDireccion, String segundaDireccion, String telefono) {
        super(id_usuario, dni, nombre, apellidos, password, imagen, tipo_Usuario, correo);
        this.id_cliente = id_cliente;
        this.primeraDireccion = primeraDireccion;
        this.segundaDireccion = segundaDireccion;
        this.telefono = telefono;
    }

    public Cliente(String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo,String primeraDireccion, String segundaDireccion, String telefono) {
        super(dni, nombre, apellidos, password, imagen, tipo_Usuario, correo);
        this.primeraDireccion = primeraDireccion;
        this.segundaDireccion = segundaDireccion;
        this.telefono = telefono;
    }

    public Cliente(String correo, String password) {
        super(correo, password);
    }

    public Cliente(String correo) {
        super(correo);
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

    @Override
    public String toString() {
        return "Cliente{" + super.toString() + "id_cliente=" + id_cliente + ", primeraDireccion=" + primeraDireccion + ", segundaDireccion=" + segundaDireccion + ", telefono=" + telefono + '}';
    }

}
