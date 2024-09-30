package br.edu.ibmec.felippe.trabalho_ap1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.edu.ibmec.felippe.trabalho_ap1.model.Cliente;
import br.edu.ibmec.felippe.trabalho_ap1.model.Endereco;
import br.edu.ibmec.felippe.trabalho_ap1.service.ClienteService;
import br.edu.ibmec.felippe.trabalho_ap1.service.EnderecoService;

@RestController
@RequestMapping("/clientes/{clienteId}/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final ClienteService clienteService;

    public EnderecoController(EnderecoService enderecoService, ClienteService clienteService) {
        this.enderecoService = enderecoService;
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco adicionarEndereco(@PathVariable int clienteId, @RequestBody Endereco endereco) {
        Cliente cliente = clienteService.buscarCliente(clienteId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        return enderecoService.adicionarEndereco(endereco, cliente);
    }

    @PutMapping("/{enderecoId}")
    public Endereco atualizarEndereco(@PathVariable int clienteId, @PathVariable int enderecoId, @RequestBody Endereco endereco) {
        return enderecoService.atualizarEndereco(enderecoId, endereco);
    }

    @DeleteMapping("/{enderecoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerEndereco(@PathVariable int enderecoId) {
        enderecoService.removerEndereco(enderecoId);
    }
}
