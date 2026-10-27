package br.com.larikaio.submissao.service;

import br.com.larikaio.submissao.model.Projeto;
import br.com.larikaio.submissao.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    @Autowired
    private Repository repository;

    public List<Projeto> listaTodos(){
        return repository.findAll();
    }

    public Optional<Projeto> buscarPorId(Long id){
        return repository.findById(id);
    }

    public Projeto cadastrar(Projeto projeto){
        projeto.setStatus("Pendente");
        return repository.save(projeto);
    }

    public Projeto editar(Long id, Projeto projetoAtualizado) {
        return repository.findById(id).map(projeto -> {
            projeto.setTitulo(projetoAtualizado.getTitulo());
            projeto.setAreaConhecimento(projetoAtualizado.getAreaConhecimento());
            return repository.save(projeto);
        }).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    }

    public  void excluir(Long id){
        repository.deleteById(id);
    }

    public Projeto avaliarProjeto(Long id, Double notaInovacao, Double notaApresentacao){
        Projeto projeto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        projeto.setNotaApresentacao(notaApresentacao);
        projeto.setNotaInovacao(notaInovacao);

        double media = (notaInovacao + notaApresentacao)/ 2.0;
        projeto.setMediaFinal(media);

        if(media >= 7.0){
            projeto.setStatus("Aprovado");
        }
        else {
            projeto.setStatus("Revisar");
        }
        return repository.save(projeto);
    }
}

