package br.com.larikaio.submissao.controller;


import br.com.larikaio.submissao.model.Projeto;
import br.com.larikaio.submissao.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projetos")
public class Controller {
    @Autowired
    private ProjetoService service;

    @GetMapping
    public List<Projeto> listar(){
        return service.listaTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscar(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Projeto cadastrar(@RequestBody Projeto projeto){
        return service.cadastrar(projeto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> editar(@PathVariable Long id, @RequestBody Projeto projeto){
        try{
            return ResponseEntity.ok(service.editar(id, projeto));
        }
        catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/avaliar")
    public ResponseEntity<Projeto> avaliar(@PathVariable Long id, @RequestBody Map<String, Double> notas){
        try{
            Double notaInovacao = notas.get("notaInovacao");
            Double notaApresentacao = notas.get("notaAprsentacao");

            Projeto projetoAvaliado = service.avaliarProjeto(id, notaInovacao, notaApresentacao);
            return ResponseEntity.ok(projetoAvaliado);
        }
        catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
