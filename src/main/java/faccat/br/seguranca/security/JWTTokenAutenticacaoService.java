/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.security;

import faccat.br.seguranca.ApplicationContextLoad;
import faccat.br.seguranca.model.Usuario;
import faccat.br.seguranca.repositories.UsuarioRepositorie;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Utilities;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author tim
 */
@Service
@Component
public class JWTTokenAutenticacaoService {
    
    //tempo de validade do token
    private static final long EXPIRATION_TIME = 172800000;
    
    //senha unica para compor a autenticacao
    private static final String SECRET = "senhaSecreta";
  
    private static final String TOKEN_PREFIX = "Bearer";
    
    private static final String HEADER_STRING = "Authorization";
    
    
    
    public void addAuthentication(HttpServletResponse response, String username, Authentication authentication) throws IOException {
         /*
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        */
        String JWT = Jwts.builder()
                .setSubject(username) //adiciona dado do usuario no token
                //.claim("roles", authorities) //se quiser colocar as roles do usuario no corpo do token descomentar isso e o codigo acima
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME)) //add tempo de expiracao
                .signWith(SignatureAlgorithm.HS512, SECRET).compact(); //tipo de criptografia
        
        
        String token = TOKEN_PREFIX+" "+JWT;   //cabeçalho de resposta "Bearer token....."
        
        response.addHeader(HEADER_STRING, token); //adiciona token ao cabeçalho da resposta -> Authorization: Bearer token.....
        
        //liberacaoCors(response); //adicionaod cabecalhos para liberar CORS
        
        //escreve o token no corpo da resposta http
        response.getWriter().write("{\"Authorization\": \""+token+"\"}");
        
    }
    
    //retorna o usuário validado com token ou caso nao esteja validado retorna null
    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response){
        
        String token = request.getHeader(HEADER_STRING);
        if(token!=null){
            String user = Jwts.parser() //pega o usuario no token criptografado
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().getSubject();
            if(user!=null){
                
                    Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepositorie.class).findByLogin(user);
                if(usuario!=null){
                    return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
                }
            }
        }
            //liberacaoCors(response); //adicionaod cabecalhos para liberar CORS
            return null; // nao autorizado
        
    }

    private void liberacaoCors(HttpServletResponse response) {
        if(response.getHeader("Access-Control-Allow-Origin")==null){
            response.addHeader("Access-Control-Allow-Origin", "*");
        }
        if(response.getHeader("Access-Control-Allow-Headers")==null){
            response.addHeader("Access-Control-Allow-Headers", "*");
        }
        if(response.getHeader("Access-Control-Request-Headers")==null){
            response.addHeader("Access-Control-Request-Headers", "*");
        }
        if(response.getHeader("Access-Control-Allow-Methods")==null){
            response.addHeader("Access-Control-Allow-Methods", "*");
        }
        
    }
    
}
