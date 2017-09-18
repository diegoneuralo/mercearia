package br.com.jetsoft.repository;

import br.com.jetsoft.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{

}
