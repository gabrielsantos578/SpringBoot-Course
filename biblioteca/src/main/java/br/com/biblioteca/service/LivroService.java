package br.com.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.entity.Livro;
import br.com.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public Livro salvarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public Optional<Livro> buscarLivroPorId(Long id) {
        return livroRepository.findById(id);
    }

    public void excluirLivro(Long id) {
        livroRepository.deleteById(id);
    }
}
