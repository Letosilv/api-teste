package com.example.api_teste.controller;

import com.example.api_teste.dto.PessoaDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaudacaoController {

    // GET simples
    @GetMapping("/saudacao")
    public String saudacao() {
        return "Olá, seja bem-vindo!";
    }

    // GET com nome
    @GetMapping("/saudacao/{nome}")
    public String saudacaoComNome(@PathVariable String nome) {
        return "Olá, " + nome + "! Seja bem-vindo!";
    }

    // POST que recebe JSON com nome e email
    @PostMapping("/pessoa")
    public String receberPessoa(@RequestBody PessoaDTO pessoa) {
        return "Pessoa recebida: Nome = " + pessoa.getNome() + ", Email = " + pessoa.getEmail();
    }
}
