package com.github.rafaelsilva91.encriptarsenhausuario.services;

import com.github.rafaelsilva91.encriptarsenhausuario.domain.Usuario;
import com.github.rafaelsilva91.encriptarsenhausuario.domain.dtos.UsuarioDTO;
import com.github.rafaelsilva91.encriptarsenhausuario.repository.UsuarioRepository;
import com.github.rafaelsilva91.encriptarsenhausuario.services.exceptions.DataIntegrityViolationException;
import com.github.rafaelsilva91.encriptarsenhausuario.services.exceptions.ObjectNotFoundExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> findAll(){
        List<Usuario> list = repository.findAll();
        return list;
    }


    public Usuario create(UsuarioDTO objDto) {
        validacaoUsuario(objDto);
        Usuario usuario = new Usuario(objDto);
        return repository.save(usuario);
    }

    private void validacaoUsuario(UsuarioDTO objDTO) {
        Optional<Usuario> obj =  repository.findByLogin(objDTO.getLogin());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Login já cadastrado no sistema!");
        }

        obj = repository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

    public Usuario findById(Integer id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.orElseThrow(()->new ObjectNotFoundExceptions("Usuario não encontrado!"));

    }
}
