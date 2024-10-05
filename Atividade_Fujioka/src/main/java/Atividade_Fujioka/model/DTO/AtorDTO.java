package Atividade_Fujioka.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtorDTO {
    private Integer id;
    private String nome;
    private Integer idade;
    private String nacionalidade;
}
