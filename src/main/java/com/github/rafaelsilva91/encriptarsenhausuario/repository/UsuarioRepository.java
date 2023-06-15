package com.github.rafaelsilva91.encriptarsenhausuario.repository;

import com.github.rafaelsilva91.encriptarsenhausuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findByLogin(String login);

    public Optional<Usuario> findByEmail(String email);

}
