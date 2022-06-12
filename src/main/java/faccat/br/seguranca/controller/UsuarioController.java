/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.controller;

import faccat.br.seguranca.DTO.UsuarioDTO;
import faccat.br.seguranca.model.Candidato;
import faccat.br.seguranca.model.Role;
import faccat.br.seguranca.model.Usuario;
import faccat.br.seguranca.repositories.CandidatoRepository;
import faccat.br.seguranca.repositories.RoleRepository;
import faccat.br.seguranca.repositories.UsuarioRepositorie;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tim
 */
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    
    public static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    
    @Autowired
    private UsuarioRepositorie repositorie;
    @Autowired
    private RoleRepository roleRep;
   
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getUsuarios(){
        List<UsuarioDTO> usuarios=repositorie.findAll().stream().map(x->new UsuarioDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }
    
    //por padrao cadastra como usuario apenas
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastraUsuario(@RequestBody Usuario usuario){
        Role role=roleRep.findById(1L).get();
        usuario.setRoles(Arrays.asList(role));
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        
        UsuarioDTO u=new UsuarioDTO(repositorie.save(usuario));
        return ResponseEntity.ok(u);
    }
}
