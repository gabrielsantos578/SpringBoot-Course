package br.com.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.biblioteca.entity.Livro;
import br.com.biblioteca.service.LivroService;

@Controller
@RequestMapping("/api/livro")  // Altere o prefixo de /api/livro para /livros
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public String criarLivro(@Valid @ModelAttribute Livro livro) {
        livroService.salvarLivro(livro);
        return "redirect:/livros";  // Redireciona para a página de listagem de livros
    }

    // Listar todos os livros
    @GetMapping
    public String listarLivros(Model model) {
        List<Livro> livros = livroService.listarLivros();
        model.addAttribute("livros", livros);
        return "livros/lista";  // Retorna a página de listagem de livros
    }

    // Buscar livro por ID
    @GetMapping("/{id}")
    public String buscarLivro(@PathVariable Long id, Model model) {
        Optional<Livro> livro = livroService.buscarLivroPorId(id);
        if (livro.isPresent()) {
            model.addAttribute("livro", livro.get());
            return "livros/detalhes";  // Exibe a página de detalhes do livro
        }
        return "redirect:/livros";  // Redireciona para a lista se não encontrar o livro
    }

    @PutMapping("/{id}")
    public String atualizarLivro(@PathVariable Long id, @Valid @ModelAttribute Livro livro) {
        if (livroService.buscarLivroPorId(id).isPresent()) {
            livro.setId(id);
            livroService.salvarLivro(livro);
            return "redirect:/livros";  // Redireciona para a página de listagem de livros após a atualização
        }
        return "redirect:/livros";  // Redireciona para a página de listagem de livros se o livro não for encontrado
    }

    // Excluir livro
    @DeleteMapping("/{id}")
    public String excluirLivro(@PathVariable Long id) {
        if (livroService.buscarLivroPorId(id).isPresent()) {
            livroService.excluirLivro(id);
            return "redirect:/livros";  // Redireciona para a página de listagem de livros após a exclusão
        }
        return "redirect:/livros";  // Redireciona para a página de listagem de livros se o livro não for encontrado
    }
}
