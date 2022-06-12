/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import faccat.br.seguranca.model.Usuario;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;


/**
 *
 * @author tim
 */
//classe gerenciador de token
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    //configurando o gerenciador de autenticacao
    protected JWTLoginFilter(String url, AuthenticationManager authenticationManager){
        // obriga a autenticar a url
        super(new AntPathRequestMatcher(url) );
        
        //gerenciador de autenticacao
        setAuthenticationManager(authenticationManager);
    }

    //retorna usuario ao processar a autenticacao
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse respose) throws AuthenticationException, IOException, ServletException {
        
        Usuario user = new ObjectMapper()
                .readValue(request.getInputStream(), Usuario.class);
        
        //retorna usuario e senha
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        
        new JWTTokenAutenticacaoService().addAuthentication(response, authResult.getName(),authResult);
    }
    
    
    
}
