package br.edu.ibmec.felippe.trabalho_ap1.service;

import org.springframework.stereotype.Service;

import br.edu.ibmec.felippe.trabalho_ap1.model.Cliente;
import br.edu.ibmec.felippe.trabalho_ap1.repository.ClienteRepository;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente adicionarCliente(Cliente cliente) {
        if (!cliente.isMaiorDeIdade()) {
            throw new IllegalArgumentException("O cliente deve ter no mínimo 18 anos.");
        }
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarCliente(int id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(int id, Cliente clienteAtualizado) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setCpf(clienteAtualizado.getCpf());
        cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        return clienteRepository.save(cliente);
    }

    public void removerCliente(int id) {
        clienteRepository.deleteById(id);
    }
}

