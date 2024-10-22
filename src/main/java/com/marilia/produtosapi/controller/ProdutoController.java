package com.marilia.produtosapi.controller;

import com.marilia.produtosapi.model.Produto;
import com.marilia.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("{id}")
    public Produto obterPorId(@PathVariable("id") UUID id){
        return produtoRepository.findById(id).orElse(null);
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") UUID id,
                          @RequestBody Produto produto){
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") UUID id){
        produtoRepository.deleteById(id);
    }

    @GetMapping
    public List<Produto> buscarPorNome(@RequestParam("nome") String nome) {
        return produtoRepository.findByNome(nome);
    }
}
