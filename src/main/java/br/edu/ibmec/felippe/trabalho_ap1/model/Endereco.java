package br.edu.ibmec.felippe.trabalho_ap1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Endereco {

  @Id
  @GeneratedValue
  private int id;

  @Column
  @NotBlank(message = "Campo obrigatório")
  private String rua;

  @Column
  @NotBlank(message = "Campo obrigatório")
  private String numero;

  @Column
  @NotBlank(message = "Campo obrigatório")
  private String bairro;

  @Column
  @NotBlank(message = "Campo obrigatório")
  private String cidade;

  @Column
  @NotBlank(message = "Campo obrigatório")
  private String estado;

  @NotBlank(message = "Campo obrigatório")
  @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato XXXXX-XXX")
  private String cep;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;


  
}
