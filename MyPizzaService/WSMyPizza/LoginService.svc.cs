using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using Modelo;

namespace WSMyPizza
{
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de clase "LoginService" en el código, en svc y en el archivo de configuración a la vez.
    // NOTA: para iniciar el Cliente de prueba WCF para probar este servicio, seleccione LoginService.svc o LoginService.svc.cs en el Explorador de soluciones e inicie la depuración.
    public class LoginService : ILoginService
    {

        private static LoginDAO model = null;

        public LoginService() {
            if (model == null) {
                model = new LoginDAO();
            }
        }

        public string addUsuario(Usuario u)
        {
            throw new NotImplementedException();
        }

        public string eliminarUsuario(Usuario u)
        {
            throw new NotImplementedException();
        }

        public Usuario loginUsuario(string correo, string password)
        {
            return model.loginUsuario(new Usuario(correo, password));
        }

        public string modificarUsuario(Usuario u)
        {
            throw new NotImplementedException();
        }
    }
}
