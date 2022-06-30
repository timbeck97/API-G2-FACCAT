/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.controller;

import faccat.br.seguranca.model.Candidato;
import faccat.br.seguranca.repositories.CandidatoRepository;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tim
 */
@RestController
@RequestMapping(value = "/candidatos")
public class CandidatoController {
    
    @Autowired
    private CandidatoRepository repositorie;
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Candidato>> getCandidatos(){
        return ResponseEntity.ok(repositorie.findAll());
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Candidato> getCandidato(@PathVariable Long id){
        return repositorie.findById(id).map(x->ResponseEntity.ok().body(x)).orElse(ResponseEntity.notFound().build());
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Candidato> salvaCandidato(@RequestBody @Valid Candidato candidato){
        return ResponseEntity.ok(repositorie.save(candidato));
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Candidato> atualizaCandidato(@RequestBody @Valid Candidato candidato, @PathVariable Long id){
        return repositorie.findById(id).map(c->{
        c.setNome(candidato.getNome());
        c.setTelefone(candidato.getTelefone());
        c.setEmail(candidato.getEmail());
        c.setEnderecoWeb(candidato.getEnderecoWeb());
        c.setExperienciaProfissional(candidato.getExperienciaProfissional());
        return ResponseEntity.ok().body(repositorie.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_ADMIN')")
    @DeleteMapping(value = "{id}") 
    public ResponseEntity<?> deleteCandidato(@PathVariable Long id){
        
           return repositorie.findById(id).map(x->{
            repositorie.deleteById(id);
            return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
          
      
        
    }
   
}
