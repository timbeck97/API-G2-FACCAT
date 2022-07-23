/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.controller;

import faccat.br.seguranca.service.ReportService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tim
 */
@RestController
@RequestMapping(value = "/relatorios")
public class ReportController {
    
    @Autowired
    private ReportService reportService;
    
    @GetMapping(value = "/candidatos")
    public ResponseEntity<String> generateReport(HttpServletRequest req){
        String report= reportService.generateReport("relatorio-candidato", req.getServletContext());
        report = "data:application/pdf;base64,"+report;
        return ResponseEntity.status(HttpStatus.OK).body(report);
    }
}
