package br.com.jetsoft.controller;

import br.com.jetsoft.model.Cliente;
import br.com.jetsoft.model.Produto;
import br.com.jetsoft.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientesController {

    @Autowired
    ClienteRepository clienteRepository;


    @RequestMapping(value = "/clientes",method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> listaClientes(Model model){
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
        if (clientes.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }

    @RequestMapping("/cliente/{id}")
    public String cliente(@PathVariable Long id, Model model){
        model.addAttribute("cliente", clienteRepository.findOne(id));
        return "cliente";
    }

    @RequestMapping(value = "/salvarCliente", method = RequestMethod.POST)
    @ResponseBody
    public String salvarCliente(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente.getIdCliente().toString();
    }

}
