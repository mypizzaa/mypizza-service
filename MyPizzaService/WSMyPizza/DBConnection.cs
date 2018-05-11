
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WSMyPizza
{
    public class DBConnection
    {
        private static readonly String HOST = "localhost";
        private static readonly String BD = "mypizza";
        private static readonly String USER = "administrator";
        private static readonly String PASSWORD = "adminpsw";
        private static MySqlConnection sqlconn;
        private static DBConnection instance = null;

        private DBConnection()
        {

        }


        public static DBConnection getInstance()
        {
            if(instance == null)
            {
                instance = new DBConnection();
            }
            return instance;
        }

        public MySqlConnection getConnection()
        {
            return sqlconn = new MySqlConnection("Server=" + HOST + ";Database=" + BD + ";Uid=" + USER + ";Pwd=" + PASSWORD + ";");
        }











    }




}
