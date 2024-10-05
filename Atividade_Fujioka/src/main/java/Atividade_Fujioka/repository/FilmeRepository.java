package Atividade_Fujioka.repository;

import Atividade_Fujioka.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByAnoDeLancamento (Integer AnoDeLancamento);

    @Query("select f.titulo from Filme f where f.titulo=: titulo")
    List<Filme> listarFilmeTitulo(@Param("titulo") String titulo);
}
