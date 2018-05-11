using Modelo;
using System.Runtime.Serialization;
using System.ServiceModel;

using System.ServiceModel.Web;
using System.Text;

namespace WSMyPizza
{
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de interfaz "IService1" en el código y en el archivo de configuración a la vez.
    [ServiceContract]
    public interface ILoginService
    {
        [OperationContract]
        Usuario loginUsuario(string correo, string password);

        [OperationContract]
        string addUsuario(Usuario u);
        [OperationContract]

        string modificarUsuario(Usuario u);

        [OperationContract]
        string eliminarUsuario(Usuario u);
    }

    #region Usuario
    [DataContract]
    public class Usuario
    {
        private long id_usuario;
        private string dni;
        private string nombre;
        private string apellidos;
        private string password;
        private string imagen;
        private string tipo_Usuario;
        private string correo;

        public Usuario(string correo, string password)
        {
            this.correo = correo;
            this.password = password;
        }

        [DataMember]
        public string Correo
        {
            get { return correo; }
            set { correo = value; }
        }

        [DataMember]
        public string Password
        {
            get { return password; }
            set { password = value; }
        }

        [DataMember]
        public string TipoUsuario
        {
            get { return tipo_Usuario; }
            set { tipo_Usuario = value; }
        }

    }
    #endregion


    // Utilice un contrato de datos, como se ilustra en el ejemplo siguiente, para agregar tipos compuestos a las operaciones de servicio.
    [DataContract]
    public class CompositeType
    {
        bool boolValue = true;
        string stringValue = "Hello ";

        [DataMember]
        public bool BoolValue
        {
            get { return boolValue; }
            set { boolValue = value; }
        }

        [DataMember]
        public string StringValue
        {
            get { return stringValue; }
            set { stringValue = value; }
        }
    }
}

