/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tim
 */
@RestController
@RequestMapping(value = "/teste")
public class TesteController {
    
    
    
    @GetMapping
    public String teste(){
        return "<h1>API FUNCIONANDO</h1>";
    }
}
