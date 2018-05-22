/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizzadao.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proven.modelo.Empleado;

/**
 *
 * @author alumne
 */
public class EmployeeDao {

    private StoreDBConnect dbConnection;

    public EmployeeDao() {
        dbConnection = new StoreDBConnect();
    }

    public List<Empleado> listAllEmployees() {
        List<Empleado> empList = null;
        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_usuario INNER JOIN tb_empleado ON tb_empleado.id_usuario = tb_usuario.id_usuario WHERE tb_usuario.activo=1");
                ResultSet rs = pst.executeQuery();
                empList = new ArrayList<Empleado>();
                while (rs.next()) {
                    empList.add(resultSetToEmployee(rs));
                }
            } catch (SQLException ex) {
            }
        }
        return empList;
    }

    public Empleado findEmployee(Empleado e) {
        Empleado emp = null;

        Connection conn = dbConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_usuario INNER JOIN tb_empleado ON tb_empleado.id_usuario = tb_usuario.id_usuario WHERE tb_usuario.dni=?");
                pst.setString(1, e.getDni());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    emp = resultSetToEmployee(rs);
                }
            } catch (SQLException ex) {
            }
        }

        return emp;
    }

    public int addEmployee(Empleado e) {
        int i = 0;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_usuario (dni, nombre, apellidos, password, imagen, tipo_usuario, correo) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, e.getDni());
                pst.setString(2, e.getNombre());
                pst.setString(3, e.getApellidos());
                pst.setString(4, e.getPassword());
                pst.setString(5, e.getImagen());
                pst.setString(6, e.getTipoUsuario());
                pst.setString(7, e.getCorreo());
                pst.executeUpdate();

                ResultSet rs = pst.getGeneratedKeys();

                if (rs.next()) {
                    long id = rs.getInt(1);
                    PreparedStatement pst1 = conn.prepareStatement("INSERT INTO tb_empleado (id_usuario, hora_entrada, hora_salida, horas_semanales, salario) VALUES(?, ?, ?, ?, ?)");
                    pst1.setLong(1, id);
                    pst1.setTime(2, e.getHoraEntrada());
                    pst1.setTime(3, e.getHoraSalida());
                    pst1.setInt(4, e.getHorasSemanales());
                    pst1.setDouble(5, e.getSalario());

                    i = pst1.executeUpdate();
                }
            } catch (SQLException ex) {
            }
        }
        return i;
    }

    public int updateEmployee(Empleado e) {
        int i = 0;
        if (e != null) {
            Connection conn = dbConnection.getConnection();
            if (conn != null) {
                try {
                    PreparedStatement pst = conn.prepareStatement("UPDATE tb_usuario SET nombre=?, apellidos=?, password=?, imagen=?, correo=? WHERE id_usuario=?");
                    pst.setString(1, e.getNombre());
                    pst.setString(2, e.getApellidos());
                    pst.setString(3, e.getPassword());
                    pst.setString(4, e.getImagen());
                    pst.setString(5, e.getCorreo());
                    pst.setLong(6, e.getIdUsuario());

                    i = pst.executeUpdate();

                    if (i > 0) {
                        PreparedStatement pst1 = conn.prepareStatement("UPDATE tb_usuario SET hora_entrada=?, hora_salida=?, horas_semanales=?, salario=? WHERE id_usuario=?");
                        pst1.setTime(2, e.getHoraEntrada());
                        pst1.setTime(3, e.getHoraSalida());
                        pst1.setInt(4, e.getHorasSemanales());
                        pst1.setDouble(5, e.getSalario());
                        pst1.setLong(1, e.getIdUsuario());
                        i = pst1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return i;
    }

    public int removeEmployee(Empleado e) {
        int i = 0;
        if (e.getDni() != null) {
            Connection conn = dbConnection.getConnection();
            if (conn != null) {
                try {
                    PreparedStatement pst = conn.prepareStatement("UPDATE tb_usuario SET activo=0 WHERE dni=?");
                    pst.setString(1, e.getDni());
                    i = pst.executeUpdate();
                } catch (SQLException ex) {
                }
            }
        }
        return i;
    }

    private Empleado resultSetToEmployee(ResultSet rs) throws SQLException {
        return new Empleado(rs.getLong("id_empleado"),
                rs.getTime("hora_entrada"), rs.getTime("hora_salida"), rs.getInt("horas_semanales"),
                rs.getDouble("salario"), rs.getLong("id_usuario"), rs.getString("dni"), rs.getString("nombre"),
                rs.getString("apellidos"), rs.getString("password"), rs.getString("imagen"),
                rs.getString("tipo_usuario"), rs.getString("correo"), rs.getInt("activo"));
    }

}
