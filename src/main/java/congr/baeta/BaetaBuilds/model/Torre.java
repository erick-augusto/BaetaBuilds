package congr.baeta.BaetaBuilds.model;

import java.util.List;

import congr.baeta.BaetaBuilds.dto.CadastroTorreDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "torre")
@Entity(name = "Torre")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "torreID")
public class Torre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long torreID;
    
    private String nomeTorre;
    private String endereco;
    private String cep;
    private int totalAptosTorre;
    
    @ManyToOne
    @JoinColumn(name = "territorioID")
    private Territorio territorio;
    
    @OneToMany(mappedBy = "torre")
    private List<Apartamento> apartamentos;
    
    public Torre(CadastroTorreDTO dados) {
        this.cep = dados.cep();
        this.endereco = dados.endereco();
        this.nomeTorre = dados.nomeTorre();
        this.totalAptosTorre = dados.totalAptosTorre();
        this.territorio = dados.territorio();
    }
}
