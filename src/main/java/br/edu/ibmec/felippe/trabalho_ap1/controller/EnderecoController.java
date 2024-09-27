package br.edu.ibmec.felippe.trabalho_ap1.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ibmec.felippe.trabalho_ap1.model.Cliente;
import br.edu.ibmec.felippe.trabalho_ap1.model.Endereco;
import br.edu.ibmec.felippe.trabalho_ap1.service.ClienteService;
import br.edu.ibmec.felippe.trabalho_ap1.service.EnderecoService;

@RestController
@RequestMapping("/clientes/{clienteId}/enderecos")
public class EnderecoController {
  private final EnderecoService enderecoService;
  private final ClienteService clienteService;

  public EnderecoController (EnderecoService enderecoService, ClienteService clienteService){
    this.enderecoService = enderecoService;
    this.clienteService = clienteService;
  }

  @PostMapping
  public Endereco adicionarEndereco (@PathVariable int clienteId, @RequestBody Endereco endereco){
   Cliente cliente = clienteService.buscarCliente(clienteId)
    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
  return enderecoService.adicionarEndereco(endereco, cliente); 
  }

  @PutMapping("/enderecoId")
  public Endereco atualizarEndereco (@PathVariable int enderecoId, @RequestBody Endereco endereco){
    return enderecoService.atualizarEndereco(enderecoId, endereco);
  }

  @DeleteMapping("/{enderecoId}")
  public void removerEndereco(@PathVariable int enderecoId){
    enderecoService.removerEndereco(enderecoId);
  }
  
}
