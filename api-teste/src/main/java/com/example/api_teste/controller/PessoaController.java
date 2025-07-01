package com.example.api_teste.controller;

import com.example.api_teste.dto.PessoaDTO;
import com.example.api_teste.model.Pessoa;
import com.example.api_teste.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    public List<Pessoa> cadastrarVariasPessoas(@RequestBody List<PessoaDTO> pessoasDTO) {
        List<Pessoa> pessoas = pessoasDTO.stream().map(dto -> {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(dto.getNome());
            pessoa.setEmail(dto.getEmail());
            return pessoa;
        }).collect(Collectors.toList());

        return pessoaRepository.saveAll(pessoas);
    }

    // Listar todas as pessoas
    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }
}
