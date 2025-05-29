package congr.baeta.BaetaBuilds.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "apartamento")
@Entity(name = "Apartamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "aptoID")
public class Apartamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aptoID;
    
    private int numApto;
    private LocalDate dataEntrega;
    private String nomeResponsavel;
    
    @ManyToOne
    @JoinColumn(name = "torreID")
    private Torre torre;

    public Apartamento(int numApto, Torre torre) {
        this.numApto = numApto;
        this.torre = torre;
    }
}
