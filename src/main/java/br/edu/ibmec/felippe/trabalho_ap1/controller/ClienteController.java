package br.edu.ibmec.felippe.trabalho_ap1.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ibmec.felippe.trabalho_ap1.model.Cliente;
import br.edu.ibmec.felippe.trabalho_ap1.repository.ClienteRepository;
import br.edu.ibmec.felippe.trabalho_ap1.service.ClienteService;

@RestController
@RequestMapping ("/clientes")
public class ClienteController {
  
  private final ClienteService clienteService;
  private final ClienteRepository clienteRepository;

  public ClienteController(ClienteService clienteService, ClienteRepository clienteRepository) {
      this.clienteService = clienteService;
      this.clienteRepository = clienteRepository;
  }  


  @GetMapping("/{id}")
  public ResponseEntity<Optional<Cliente>> buscarCliente(@PathVariable int id) {
    Optional<Cliente> cliente = clienteService.buscarCliente(id);
    if (cliente == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(cliente);
}

  @PostMapping
  public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente) throws Exception {
    Cliente novoCliente = clienteService.adicionarCliente(cliente);
    return ResponseEntity.ok(novoCliente);
}


  @PutMapping("/{id}")
  public Cliente atualizarCliente(@PathVariable int id, @RequestBody Cliente cliente){
    return clienteService.atualizarCliente(id, cliente);

  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removerCliente (@PathVariable int id){
    clienteService.removerCliente(id);
  }

    
}
