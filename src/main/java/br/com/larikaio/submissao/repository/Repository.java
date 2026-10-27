package br.com.larikaio.submissao.repository;

import br.com.larikaio.submissao.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Projeto, Long> {
}
