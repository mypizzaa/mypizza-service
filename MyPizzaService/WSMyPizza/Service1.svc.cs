using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using Modelo;

namespace WSMyPizza
{
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de clase "Service1" en el código, en svc y en el archivo de configuración.
    // NOTE: para iniciar el Cliente de prueba WCF para probar este servicio, seleccione Service1.svc o Service1.svc.cs en el Explorador de soluciones e inicie la depuración.
    public class WSLogin : IWSLogin
    {

        private LoginDAO model = null;    
        public WSLogin ()
        {
            if (model == null)
            {
                model = new LoginDAO();
            }
        }

        public bool addUsuario(Usuario u)
        {
            throw new NotImplementedException();
        }

        public bool eliminarUsuario(Usuario u)
        {
            throw new NotImplementedException();
        }

        public Usuario LoginUsuario(string correo, string password)
        {
            return model.loginUsuario(correo, password);
        }

        public bool modificarUsuario(Usuario u)
        {
            throw new NotImplementedException();
        }
    }
}
