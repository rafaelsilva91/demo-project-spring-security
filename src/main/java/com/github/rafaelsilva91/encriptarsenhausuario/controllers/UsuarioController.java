package com.github.rafaelsilva91.encriptarsenhausuario.controllers;

import com.github.rafaelsilva91.encriptarsenhausuario.domain.Usuario;
import com.github.rafaelsilva91.encriptarsenhausuario.domain.dtos.UsuarioDTO;
import com.github.rafaelsilva91.encriptarsenhausuario.repository.UsuarioRepository;
import com.github.rafaelsilva91.encriptarsenhausuario.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioService service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        List<Usuario> list = service.findAll();
        List<UsuarioDTO> listDTO = list.stream().map(obj-> new UsuarioDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id){
        Usuario obj = service.findById(id);
        UsuarioDTO objDTO = new UsuarioDTO(obj);
        return ResponseEntity.ok().body(objDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO objDto){
        //System.out.println("senha: "+objDto.getPassword());
        objDto.setPassword(encoder.encode(objDto.getPassword()));
        //System.out.println("senha encriptada: "+objDto.getPassword());
        Usuario usuario = service.create(objDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/validaSenha")
    public ResponseEntity<Boolean> validPassword(@RequestParam String login,
                                                 @RequestParam String password){

        Usuario usuario = service.findByLogin(login);

        if(usuario == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean valid = false;
        valid = encoder.matches(password, usuario.getPassword());
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }

}
