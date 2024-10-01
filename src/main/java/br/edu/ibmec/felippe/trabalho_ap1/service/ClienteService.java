package br.edu.ibmec.felippe.trabalho_ap1.service;

import org.springframework.stereotype.Service;

import br.edu.ibmec.felippe.trabalho_ap1.exception.ClienteException;
import br.edu.ibmec.felippe.trabalho_ap1.model.Cliente;
import br.edu.ibmec.felippe.trabalho_ap1.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente adicionarCliente(Cliente cliente) throws Exception{

// Valida o novo cliente
        if (!validarCliente(cliente)){
            throw new Exception("Dados inválidos");
        }
//Verifica se email já foi cadastrado
        Optional<Cliente> opClienteEmail = this.clienteRepository.findClienteByEmail(cliente.getEmail());
        if (opClienteEmail.isPresent()) {
            throw new ClienteException("Este email já foi utilizado");
        }

// Verifica se o CPF já está cadastrado
        Optional<Cliente> opClienteCpf = this.clienteRepository.findClienteByCpf(cliente.getCpf());
        if (opClienteCpf.isPresent()) {
            throw new ClienteException("Este CPF já foi cadastrado");
        }
        
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarCliente(int id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(int id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        // Verificar se o CPF ou email está sendo alterado e se já existe
        validarAtualizacao(clienteAtualizado, clienteExistente);

        // Atualizar informações do cliente
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setCpf(clienteAtualizado.getCpf());
        clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());

        return clienteRepository.save(clienteExistente);
    }

    public void removerCliente(int id) {
        clienteRepository.deleteById(id);
    }

    private boolean validarCliente(Cliente cliente) {
    if (!cliente.isMaiorDeIdade()) {
        throw new IllegalArgumentException("O cliente deve ter no mínimo 18 anos.");
    }
    // Se todas as validações passarem, retorne true
    return true;
}


    private void validarAtualizacao(Cliente clienteAtualizado, Cliente clienteExistente) {
        
    }

    public List<Cliente> listarTodosClientes() {
      return clienteRepository.findAll();
    }
}
