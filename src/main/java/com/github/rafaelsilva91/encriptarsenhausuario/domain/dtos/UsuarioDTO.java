package com.github.rafaelsilva91.encriptarsenhausuario.domain.dtos;

import com.github.rafaelsilva91.encriptarsenhausuario.domain.Usuario;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "O campo LOGIN é requerido")
    private String login;

    @NotNull(message = "O campo Email é requerido")
    private String email;

    @NotNull(message = "O campo PASSWORD é requerido")
    private String password;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.login = obj.getLogin();
        this.password = obj.getPassword();
        this.email = obj.getEmail();
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
}
