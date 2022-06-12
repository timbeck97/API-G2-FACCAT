/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.service;

import faccat.br.seguranca.model.Usuario;
import faccat.br.seguranca.repositories.UsuarioRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author tim
 */
@Service
public class ImplementacaoUserDetailService implements UserDetailsService{

    @Autowired
    private UsuarioRepositorie usuarioRepositorie;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepositorie.findByLogin(username);
        if(user==null){
            throw new UsernameNotFoundException("Usuario nao encontrado: "+username);
        }
        else{
           return new User(user.getLogin(), user.getPassword(), user.getAuthorities());
        }
        
    }
    
}
