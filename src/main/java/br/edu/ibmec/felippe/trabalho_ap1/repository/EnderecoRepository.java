package br.edu.ibmec.felippe.trabalho_ap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ibmec.felippe.trabalho_ap1.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {


  
}
