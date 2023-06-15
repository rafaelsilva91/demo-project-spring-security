package com.github.rafaelsilva91.encriptarsenhausuario.controllers;

import com.github.rafaelsilva91.encriptarsenhausuario.domain.Usuario;
import com.github.rafaelsilva91.encriptarsenhausuario.domain.dtos.UsuarioDTO;
import com.github.rafaelsilva91.encriptarsenhausuario.repository.UsuarioRepository;
import com.github.rafaelsilva91.encriptarsenhausuario.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
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
    public ResponseEntity<UsuarioDTO> insert(@Valid @RequestBody UsuarioDTO objDto){
        Usuario usuario = service.create(objDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
