package Atividade_Fujioka.service;

import Atividade_Fujioka.model.DTO.FilmeDTO;
import Atividade_Fujioka.model.Filme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public List<FilmeDTO> listarTodos() {
        return filmeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FilmeDTO> buscarPorId(Long id) {
        return filmeRepository.findById(id)
                .map(this::convertToDTO);
    }

    public FilmeDTO criarFilme(FilmeDTO filmeDTO) {
        Filme filme = convertToEntity(filmeDTO);
        return convertToDTO(filmeRepository.save(filme));
    }

    public Optional<FilmeDTO> atualizarFilme(Long id, FilmeDTO filmeDTO) {
        return filmeRepository.findById(id).map(filme -> {
            filme.setTitulo(filmeDTO.getTitulo());
            filme.setAnoDeLancamento(filmeDTO.getAnoDeLancamento());
            filme.setDiretor(convertToEntity(filmeDTO.getDiretor()));
            filme.setAtores(filmeDTO.getAtores().stream().map(this::convertToEntity).collect(Collectors.toList()));
            filme.setGeneros(filmeDTO.getGeneros().stream().map(this::convertToEntity).collect(Collectors.toList()));
            return convertToDTO(filmeRepository.save(filme));
        });
    }

    public boolean deletarFilme(Long id) {
        if (filmeRepository.existsById(id)) {
            filmeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private FilmeDTO convertToDTO(Filme filme) {
        return new FilmeDTO(
                filme.getId(),
                filme.getTitulo(),
                filme.getAnoDeLancamento(),
                filme.getDiretor() != null ? new DiretorDTO(filme.getDiretor().getId(), filme.getDiretor().getNome()) : null,
                filme.getAtores()
                        .stream()
                        .map(ator -> new AtorDTO(ator.getId(), ator.getNome()))
                        .collect(Collectors.toList()),
                filme.getGeneros()
                        .stream()
                        .map(genero -> new GeneroDTO(genero.getId(), genero.getNome()))
                        .collect(Collectors.toList())
        );
    }


    private Filme convertToEntity(FilmeDTO filmeDTO) {
        Filme filme = new Filme();
        filme.setTitulo(filmeDTO.getTitulo());
        filme.setAnoDeLancamento(filmeDTO.getAnoDeLancamento());
        filme.setDiretor(convertToEntity(filmeDTO.getDiretor()));
        filme.setAtores(filmeDTO.getAtores()
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
        filme.setGeneros(filmeDTO.getGeneros()
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
        return filme;
    }

    private Diretor convertToEntity(DiretorDTO diretorDTO) {
        if (diretorDTO == null) {
            return null; // Retorne null caso não haja Diretor
        }
        Diretor diretor = new Diretor();
        diretor.setId(diretorDTO.getId());
        diretor.setNome(diretorDTO.getNome());
        return diretor;
    }

    private Ator convertToEntity(AtorDTO atorDTO) {
        Ator ator = new Ator();
        ator.setId(atorDTO.getId());
        ator.setNome(atorDTO.getNome());
        return ator;
    }

    private Genero convertToEntity(GeneroDTO generoDTO) {
        Genero genero = new Genero();
        genero.setId(generoDTO.getId());
        genero.setNome(generoDTO.getNome());
        return genero;
    }
}
