/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


/**
 *
 * @author tim
 */
@Service
public class ReportService implements Serializable{
    
   
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public String generateReport(String reportName, ServletContext servletContext){
        Connection connection=null;
        try {
           
            connection = jdbcTemplate.getDataSource().getConnection();
            
           
            //String path = this.getClass().getClassLoader().getResource("reports").getPath();
            //String pathReport= path+File.separator+reportName + ".jasper";
         
            Resource resource = new ClassPathResource("reports/"+reportName+".jasper");
            //opcao abaixo dava erro no docker ao tentar pegar o path... ai foi necessario usar essa classe Resource acima pra fazer o input do arquivo do jasper
            //JasperPrint print = JasperFillManager.fillReport(pathReport, new HashMap<>(), connection);
            
            JasperPrint print = JasperFillManager.fillReport(resource.getInputStream(), new HashMap<>(), connection);
            byte[] reportBytes = JasperExportManager.exportReportToPdf(print);
            return Base64.encodeBase64String(reportBytes);
                    
        } catch (SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(connection!=null)
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
        
    }
}
