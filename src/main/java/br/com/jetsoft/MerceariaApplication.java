package br.com.jetsoft;

import br.com.jetsoft.model.Cliente;
import br.com.jetsoft.model.Pedido;
import br.com.jetsoft.model.Produto;
import br.com.jetsoft.repository.ClienteRepository;
import br.com.jetsoft.repository.PedidoRepository;
import br.com.jetsoft.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class MerceariaApplication implements CommandLineRunner {

	@Autowired
    ProdutoRepository produtoRepository;

	@Autowired
    ClienteRepository clienteRepository;

	@Autowired
    PedidoRepository pedidoRepository;



    public static void main(String[] args) {
		SpringApplication.run(MerceariaApplication.class, args);
	}


    @Override
    public void run(String... strings) throws Exception {

        //mock produtos
        Produto mocha = new Produto();
        mocha.setNomeProduto("Mocha");
        mocha.setQuantidade(10);
        mocha.setPrecoProduto(3.95);

        Produto capuccinno = new Produto();
        capuccinno.setNomeProduto("Capuccinno");
        capuccinno.setQuantidade(5);
        capuccinno.setPrecoProduto(4.95);

        produtoRepository.save(mocha);
        produtoRepository.save(capuccinno);

        //mock clientes
        Cliente cliente1 = new Cliente();
        cliente1.setNomeCliente("Diego");
        cliente1.setSobrenomeCliente("Costa");

        Cliente cliente2 = new Cliente();
        cliente2.setNomeCliente("Roberto");
        cliente2.setSobrenomeCliente("Ferreira");

        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);


    }


}
