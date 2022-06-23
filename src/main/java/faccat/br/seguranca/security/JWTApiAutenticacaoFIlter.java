/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author tim
 */
//FIltro onde todas requisicoes sao filtradas para autenticar
public class JWTApiAutenticacaoFIlter extends GenericFilterBean{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       //estabelece a autenticacao para a requisicao
        Authentication authentication = new JWTTokenAutenticacaoService().getAuthentication((HttpServletRequest)request, (HttpServletResponse)response);
        //coloca o processo de autenticacao no spring security
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        chain.doFilter(request, response);
    }
    
}
