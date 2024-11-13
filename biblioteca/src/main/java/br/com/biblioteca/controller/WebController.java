package br.com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.biblioteca.entity.Livro;
import br.com.biblioteca.service.LivroService;

@Controller
public class WebController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/livros")
    public String showLivroPage(Model model) {
        model.addAttribute("livros", livroService.listarLivros());
        return "list"; // Nome do arquivo livro.html em src/main/resources/templates
    }

    @GetMapping("/livros/cadastrar")
    public String showNovoLivroForm(Model model) {
        model.addAttribute("livro", new Livro());
        return "form";
    }

    @GetMapping("/livros/editar/{id}")
    public String showEditarLivroForm(@PathVariable Long id, Model model) {
        Livro livro = livroService.buscarLivroPorId(id).orElse(new Livro());
        model.addAttribute("livro", livro);
        return "form";
    }
}
