/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.DTO;

import faccat.br.seguranca.model.Role;
import faccat.br.seguranca.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tim
 */
public class UsuarioDTO {
    private String login;
    private List<Role> roles=new ArrayList<>();
    
    
    public UsuarioDTO(Usuario usuario){
        this.login=usuario.getLogin();
        usuario.getAuthorities().forEach(x->this.roles.add((Role)x));
    }

 
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
}
