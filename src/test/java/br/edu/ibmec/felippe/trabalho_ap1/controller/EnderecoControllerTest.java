package br.edu.ibmec.felippe.trabalho_ap1.controller;

import br.edu.ibmec.felippe.trabalho_ap1.model.Cliente;
import br.edu.ibmec.felippe.trabalho_ap1.model.Endereco;
import br.edu.ibmec.felippe.trabalho_ap1.service.ClienteService;
import br.edu.ibmec.felippe.trabalho_ap1.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@WebMvcTest(EnderecoController.class)
@AutoConfigureMockMvc
public class EnderecoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private EnderecoService enderecoService;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void should_add_endereco() throws Exception {
        Endereco endereco = new Endereco();
        endereco.setId(1);
        endereco.setRua("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setCep("00000-000");

        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Cliente Teste");

        given(clienteService.buscarCliente(1)).willReturn(Optional.of(cliente));
        given(enderecoService.adicionarEndereco(endereco, cliente)).willReturn(endereco);

        mvc.perform(MockMvcRequestBuilders
                .post("/clientes/1/enderecos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(endereco)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value("Rua Teste"));
    }

    @Test
    public void should_update_endereco() throws Exception {
        Endereco endereco = new Endereco();
        endereco.setId(1);
        endereco.setRua("Rua Atualizada");
        endereco.setCidade("Cidade Atualizada");
        endereco.setCep("00000-111");

        given(enderecoService.atualizarEndereco(1, endereco)).willReturn(endereco);

        mvc.perform(MockMvcRequestBuilders
                .put("/clientes/1/enderecos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(endereco)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value("Rua Atualizada"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value("00000-111"));
    }

    @Test
    public void should_delete_endereco() throws Exception {
        doNothing().when(enderecoService).removerEndereco(1);

        mvc.perform(MockMvcRequestBuilders
                .delete("/clientes/1/enderecos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

