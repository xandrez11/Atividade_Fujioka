package Atividade_Fujioka.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmeDTO {
    private Integer id;
    private String titulo;
    private Integer anoDeLancamento;
    private DiretorDTO diretor;
    private List<AtorDTO> atores;
    private List<GeneroDTO> generos;
}

