package br.edu.ibmec.felippe.trabalho_ap1.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ibmec.felippe.trabalho_ap1.model.Cliente;
import br.edu.ibmec.felippe.trabalho_ap1.repository.ClienteRepository;
import br.edu.ibmec.felippe.trabalho_ap1.service.ClienteService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.Matchers.is;

@AutoConfigureMockMvc
@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {

  @MockBean
  private ClienteRepository clienteRepository;

  @MockBean
  private ClienteService clienteService;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;

  @BeforeEach
  public void setup() {
    this.mvc = MockMvcBuilders
      .webAppContextSetup(context)
      .build();
  }

  @Test
  public void should_create_cliente() throws Exception {
    Cliente cliente = new Cliente();
    cliente.setId(0);
    cliente.setNome("Teste");
    cliente.setCpf("999.000.111-22");
    cliente.setDataNascimento(LocalDate.of(2000, 01, 02));
    cliente.setEmail("teste@teste.com");
    cliente.setTelefone("(00) 9999-7777");
    cliente.setEnderecos(null);
    
    given(this.clienteService.adicionarCliente(cliente)).willReturn(cliente);

    this.mvc.perform(MockMvcRequestBuilders
        .post("/clientes")
        .content(this.mapper.writeValueAsString(cliente))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())  // Status 200, pois ResponseEntity.ok() é usado
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is("Teste")));
  }

  @Test
  public void should_get_cliente() throws Exception {
    Cliente cliente = new Cliente();
    cliente.setId(1);
    cliente.setNome("Lorem Ipsum One");
    cliente.setCpf("000.777.888-44");
    cliente.setDataNascimento(LocalDate.of(2000, 01, 03));
    cliente.setEmail("lorem@ipsom.com");
    cliente.setTelefone("(99) 8888-8787");
    cliente.setEnderecos(null);

    given(this.clienteRepository.findById(1)).willReturn(Optional.of(cliente));

    this.mvc.perform(MockMvcRequestBuilders
        .get("/clientes/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1))); 
  }

  @Test
  public void should_get_cliente_with_not_found() throws Exception {
    // Mock para cliente não encontrado
    given(this.clienteRepository.findById(1)).willReturn(Optional.empty());

    this.mvc.perform(MockMvcRequestBuilders
        .get("/clientes/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void should_delete_cliente() throws Exception {
    // Mock para remover cliente
    doNothing().when(clienteService).removerCliente(1);

    this.mvc.perform(MockMvcRequestBuilders
        .delete("/clientes/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());  // Status 204 esperado para DELETE
  }

  @Test
  public void should_update_cliente() throws Exception {
    Cliente cliente = new Cliente();
    cliente.setId(1);
    cliente.setNome("Nome Atualizado");
    cliente.setCpf("111.111.111-11");
    cliente.setEmail("atualizado@teste.com");

    given(clienteService.atualizarCliente(1, cliente)).willReturn(cliente);

    this.mvc.perform(MockMvcRequestBuilders
        .put("/clientes/1")
        .content(mapper.writeValueAsString(cliente))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is("Nome Atualizado")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.cpf", is("111.111.111-11")));
  }
}
