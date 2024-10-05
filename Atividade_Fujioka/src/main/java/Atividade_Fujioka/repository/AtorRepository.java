package Atividade_Fujioka.repository;

import Atividade_Fujioka.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {

    // Busca atores por idade
    List<Ator> findByIdade(Integer idade);

    // Busca atores por nome
    @Query("select a from Ator a where a.nome = :nome")
    List<Ator> listarAtorPorNome(@Param("nome") String nome);
}
