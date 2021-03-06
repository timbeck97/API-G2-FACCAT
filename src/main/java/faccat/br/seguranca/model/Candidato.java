
package faccat.br.seguranca.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;


/**
 *
 * @author tim
 */
@Entity
public class Candidato implements Serializable {
    
    @SequenceGenerator(name = "SEQ_CANDIDATO", allocationSize = 1, sequenceName = "candidato_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CANDIDATO")
    @Id
    private Long id;
    @Pattern(message="Aceita apenas numeros ou letras", regexp = "[a-zA-Z0-9 ]+")
    @NotNull
    private String nome;
    @Pattern(message="Aceita apenas numeros ou letras", regexp = "[a-zA-Z0-9 ]+")
    @NotNull
    private String telefone;
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")
    private Date dataNascimento;

    private String email;
    
    
    private String enderecoWeb;
    
    
    @NotNull
    private String experienciaProfissional;
    
    
    public Candidato(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnderecoWeb() {
        return enderecoWeb;
    }

    public void setEnderecoWeb(String enderecoWeb) {
        this.enderecoWeb = enderecoWeb;
    }

    public String getExperienciaProfissional() {
        return experienciaProfissional;
    }

    public void setExperienciaProfissional(String experienciaProfissional) {
        this.experienciaProfissional = experienciaProfissional;
    }
    
}
