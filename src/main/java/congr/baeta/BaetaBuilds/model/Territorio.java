package congr.baeta.BaetaBuilds.model;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "territorio")
@Entity(name = "Territorio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "territorioID")
public class Territorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long territorioID;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int totalAptos;

    @OneToMany(mappedBy = "territorio")
    private List<Torre> torres;

    public Territorio(int totalAptos) {
        this.totalAptos = totalAptos;
    }
}
