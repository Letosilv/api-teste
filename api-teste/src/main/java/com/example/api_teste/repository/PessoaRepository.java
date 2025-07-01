package com.example.api_teste.repository;

import com.example.api_teste.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByEmail(String email);

    // ✅ Método necessário para evitar duplicação em lote
    List<Pessoa> findByEmailIn(List<String> emails);
}
