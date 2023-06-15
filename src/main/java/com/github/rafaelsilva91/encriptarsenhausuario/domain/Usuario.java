package com.github.rafaelsilva91.encriptarsenhausuario.domain;

import com.github.rafaelsilva91.encriptarsenhausuario.domain.dtos.UsuarioDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String login;

    @Column(unique = true)
    private String email;

    private String password;

    public Usuario() {
    }

    public Usuario(Integer id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Usuario(UsuarioDTO objDto) {
        this.id = objDto.getId();
        this.login = objDto.getLogin();
        this.email = objDto.getEmail();
        this.password = objDto.getPassword();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        if (getId() != null ? !getId().equals(usuario.getId()) : usuario.getId() != null) return false;
        return getLogin() != null ? getLogin().equals(usuario.getLogin()) : usuario.getLogin() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
