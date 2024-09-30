package br.edu.ibmec.felippe.trabalho_ap1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ibmec.felippe.trabalho_ap1.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
  Optional<Cliente> findClienteByEmail(String email);
  Optional<Cliente> findClienteByCpf(String cpf); 

    
}
