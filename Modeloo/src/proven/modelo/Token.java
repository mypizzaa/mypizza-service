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
    private Usuario usuario;
    private String token;
    private Timestamp date_time;

    public Token(long id_token, Usuario usuario, String token, Timestamp date_time) {
        this.id_token = id_token;
        this.usuario = usuario;
        this.token = token;
        this.date_time = date_time;
    }

    public Token(Usuario usuario, String token, Timestamp date_time) {
        this.usuario = usuario;
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

    public Usuario getId_usuario() {
        return usuario;
    }

    public void setId_usuario(Usuario usuario) {
        this.usuario = usuario;
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
        return "Token{" + "id_token=" + id_token + ", usuario=" + usuario + ", token=" + token + ", date_time=" + date_time + '}';
    }   
}
