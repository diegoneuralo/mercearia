package br.com.jetsoft.controller;

import br.com.jetsoft.model.Cliente;
import br.com.jetsoft.model.Pedido;
import br.com.jetsoft.model.Produto;
import br.com.jetsoft.repository.ClienteRepository;
import br.com.jetsoft.repository.PedidoRepository;
import br.com.jetsoft.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PedidosController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping(value = "/pedidos", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> listaPedidos(Model model){
        List<Pedido> pedidos = (List<Pedido>) pedidoRepository.findAll();
        if (pedidos.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
    }

    @RequestMapping(value="/criarPedido", method = RequestMethod.POST)
    @ResponseBody
    public String salvarPedido(@RequestParam String nomeCliente, @RequestParam String sobrenomeCliente, @RequestParam(value="idProdutos[]") Long[] idProdutos){

        Cliente cliente = new Cliente();
        cliente.setNomeCliente(nomeCliente);
        cliente.setSobrenomeCliente(sobrenomeCliente);
        clienteRepository.save(cliente);
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteRepository.findOne(cliente.getIdCliente()));
        Set<Produto> produtoSet = new HashSet<Produto>();
        for (Long idProduto:idProdutos){
            produtoSet.add(produtoRepository.findOne(idProduto));
        }
        pedido.setProdutos(produtoSet);
        Double total = 0.0;
        for (Long idProduto:idProdutos){
            total = total + (produtoRepository.findOne(idProduto).getPrecoProduto());
        }
        pedido.setTotal(total);
        pedidoRepository.save(pedido);

        return pedido.getIdPedido().toString();
    }

    @RequestMapping(value = "/removerPedido", method = RequestMethod.POST)
    @ResponseBody
    public String removerPedido(@RequestParam Long Id){
        pedidoRepository.delete(Id);
        return Id.toString();
    }
}