package br.edu.ibmec.felippe.trabalho_ap1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Endereco {

  @Id
  @GeneratedValue
  private int id;

  @Column
  @NotBlank(message = "Campo obrigatório")
  @Size(min = 3, max = 255, message = "Rua deve ter entre 3 e 255 caracteres")
  private String rua;

  @Column
  @NotBlank(message = "Campo obrigatório")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Número deve conter apenas caracteres alfanuméricos ou numéricos")
  private String numero;

  @Column
  @NotBlank(message = "Campo obrigatório")
  @Size(min = 3, max = 100, message = "Rua deve ter entre 3 e 100 caracteres")
  private String bairro;

  @Column
  @NotBlank(message = "Campo obrigatório")
  @Size(min = 2, max = 100, message = "Rua deve ter entre 2 e 100 caracteres")
  private String cidade;

  @Column
  @NotBlank(message = "Campo obrigatório")
  @Pattern(regexp = "^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$", message = "Estado deve conter uma sigla válida")
  private String estado;

  @NotBlank(message = "Campo obrigatório")
  @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato XXXXX-XXX")
  private String cep;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;
  
}
