package br.edu.ibmec.felippe.trabalho_ap1.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ibmec.felippe.trabalho_ap1.model.Cliente;
import br.edu.ibmec.felippe.trabalho_ap1.service.ClienteService;

@RestController
@RequestMapping ("/clientes")
public class ClienteController {
  private final ClienteService clienteService;

  public ClienteController(ClienteService clienteService){
    this.clienteService = clienteService;
  }
 
  @PostMapping
  public Cliente adicionarCliente(@RequestBody Cliente cliente){
    return clienteService.adicionarCliente(cliente);
  }

  @GetMapping("/{id}")
  public Cliente atualizarCliente(@PathVariable int id, @RequestBody Cliente cliente){
    return clienteService.atualizarCliente(id, cliente);

  }

  @DeleteMapping("/{id}")
  public void removerCliente (@PathVariable int id){
    clienteService.removerCliente(id);
  }

    
}
