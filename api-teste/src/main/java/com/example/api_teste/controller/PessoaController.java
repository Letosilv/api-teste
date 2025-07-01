package com.example.api_teste.controller;

import com.example.api_teste.dto.PessoaDTO;
import com.example.api_teste.model.Pessoa;
import com.example.api_teste.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarVariasPessoas(@RequestBody List<PessoaDTO> pessoasDTO) {
        // Coletar e-mails enviados
        List<String> emailsRecebidos = pessoasDTO.stream()
                .map(PessoaDTO::getEmail)
                .toList();

        // Verificar se algum e-mail já existe no banco
        List<Pessoa> jaCadastradas = pessoaRepository.findByEmailIn(emailsRecebidos);
        if (!jaCadastradas.isEmpty()) {
            List<String> duplicados = jaCadastradas.stream()
                    .map(Pessoa::getEmail)
                    .toList();
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("E-mails já cadastrados: " + String.join(", ", duplicados));
        }

        // Se não houver duplicados, salva
        List<Pessoa> pessoas = pessoasDTO.stream().map(dto -> {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(dto.getNome());
            pessoa.setEmail(dto.getEmail());
            return pessoa;
        }).collect(Collectors.toList());

        List<Pessoa> salvas = pessoaRepository.saveAll(pessoas);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvas);
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }
}
