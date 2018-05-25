/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.modelo;

import java.sql.Timestamp;

/**
 *
 * @author alumne
 */
public class Token {
    
    private long id_token;
    private long id_usuario;
    private String token;
    private Timestamp date_time;

    public Token(long id_token, long id_usuario, String token, Timestamp date_time) {
        this.id_token = id_token;
        this.id_usuario = id_usuario;
        this.token = token;
        this.date_time = date_time;
    }

    public Token() {
    }

    public long getId_token() {
        return id_token;
    }

    public void setId_token(long id_token) {
        this.id_token = id_token;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    @Override
    public String toString() {
        return "Token{" + "id_token=" + id_token + ", id_usuario=" + id_usuario + ", token=" + token + ", date_time=" + date_time + '}';
    }   
}
