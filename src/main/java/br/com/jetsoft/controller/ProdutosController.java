package br.com.jetsoft.controller;

import br.com.jetsoft.model.Pedido;
import br.com.jetsoft.model.Produto;
import br.com.jetsoft.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProdutosController {

    @Autowired
    ProdutoRepository produtoRepository;


    @RequestMapping(value = "/produtos",method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> listaProdutos(Model model){
        List<Produto> produtos = (List<Produto>) produtoRepository.findAll();
        if (produtos.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
    public ResponseEntity selecionarProduto(@PathVariable Long id){
        Produto produto = produtoRepository.findOne(id);
        return new ResponseEntity(produto, HttpStatus.OK);
    }

    @RequestMapping(value = "/salvarProduto", method = RequestMethod.POST)
    @ResponseBody
    public String salvarProduto(@RequestBody Produto produto) {
        produtoRepository.save(produto);
        return produto.getIdProduto().toString();
    }

    @RequestMapping(value = "/removerProduto", method = RequestMethod.DELETE)
    @ResponseBody
    public String removerProduto(@PathVariable Long id) {
        produtoRepository.delete(id);
        return id.toString();
    }

}
