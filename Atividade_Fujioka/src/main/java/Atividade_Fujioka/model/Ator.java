package Atividade_Fujioka.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Ator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private Integer idade;
    private String nacionalidade;

    @ManyToMany
    @JoinTable(
            name = "filme_ator",
            joinColumns = @JoinColumn(name = "ator_id"),
            inverseJoinColumns = @JoinColumn(name = "filme_id")
    )
    private List<Filme> filmes;
}
