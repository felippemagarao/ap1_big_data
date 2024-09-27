package br.edu.ibmec.felippe.trabalho_ap1.service;

import org.springframework.stereotype.Service;

import br.edu.ibmec.felippe.trabalho_ap1.model.Cliente;
import br.edu.ibmec.felippe.trabalho_ap1.model.Endereco;
import br.edu.ibmec.felippe.trabalho_ap1.repository.EnderecoRepository;

@Service
public class EnderecoService {
  private final EnderecoRepository enderecoRepository;

  public EnderecoService(EnderecoRepository enderecoRepository){
    this.enderecoRepository = enderecoRepository;
  }

  public Endereco adicionarEndereco(Endereco endereco, Cliente cliente){
    endereco.setCliente(cliente);
    return enderecoRepository.save(endereco);
  }

  public Endereco atualizarEndereco (int id, Endereco enderecoAtualizado){
    Endereco endereco = enderecoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado"));
    endereco.setRua(enderecoAtualizado.getRua());
    endereco.setNumero(enderecoAtualizado.getNumero());
    endereco.setBairro(enderecoAtualizado.getBairro());
    endereco.setCidade(enderecoAtualizado.getCidade());
    endereco.setEstado(enderecoAtualizado.getEstado());
    endereco.setCep(enderecoAtualizado.getCep());
    return enderecoRepository.save(endereco);  
  }

  public void removerEndereco(int id){
    enderecoRepository.deleteById(id);
  }
}
