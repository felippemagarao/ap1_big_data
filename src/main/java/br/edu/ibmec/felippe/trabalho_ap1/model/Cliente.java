package br.edu.ibmec.felippe.trabalho_ap1.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Cliente {

  @Id
  @GeneratedValue
  private int id;

  @Column
  @NotBlank (message = "Nome é obrigatorio")
  @Size(min=3, max =100, message = "O nome deve ter entre 3 e 100 caracteres")
  private String nome;

  @Column
  @NotBlank(message = "Email é obrigatorio")
  @Email(message = "O email deve ser válido")
  private String email;

  @Column
  @NotBlank(message = "CPF é obrigatorio")
  @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato XXX.XXX.XXX-XX")
  private String cpf;

  @Column
  @NotNull(message = "Data de nascimento é obrigatoria")
  private LocalDate dataNascimento;

  @Column
  @Pattern(regexp = "\\(\\d{2}\\) \\d{4}-\\d{4}", message = "Telefone deve estar no formato (XX) XXXX-XXXX")
  private String telefone;

  @Column
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Endereco> enderecos;


  public boolean isMaiorDeIdade(){
    return Period.between(this.dataNascimento, LocalDate.now()).getYears() >=18;
  }
}
