/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.security;

import faccat.br.seguranca.service.ImplementacaoUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



/**
 *
 * @author tim
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private ImplementacaoUserDetailService implementacaoUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .disable().authorizeRequests().antMatchers("/").permitAll()
        .antMatchers("/index").permitAll()
        .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()) , UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new JWTApiAutenticacaoFIlter(), UsernamePasswordAuthenticationFilter.class);
                
        
        //falta filtros de login para autenticacao
        
        //filtro requisicoes para verificar token
                
    }

    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        
        auth
        .userDetailsService(implementacaoUserDetailService) //Servico que ira consultar o usuario no banco de dados
        .passwordEncoder(new BCryptPasswordEncoder()); //codificacao de senha bcrypt
    }
    
    
    
}
