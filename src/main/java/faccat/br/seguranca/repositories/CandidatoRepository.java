/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faccat.br.seguranca.repositories;

import faccat.br.seguranca.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tim
 */
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    
}
