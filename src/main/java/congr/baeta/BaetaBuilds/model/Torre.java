package congr.baeta.BaetaBuilds.model;

import java.util.List;

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
import lombok.Setter;

@Table(name = "torre")
@Entity(name = "Torre")
@Getter
@Setter
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

    public Torre(String nomeTorre, String endereco, String cep, Integer totalAptos, Territorio territorio) {
        this.nomeTorre = nomeTorre;
        this.endereco = endereco;
        this.cep = cep;
        this.totalAptosTorre = totalAptos;
        this.territorio = territorio;
    }

}
