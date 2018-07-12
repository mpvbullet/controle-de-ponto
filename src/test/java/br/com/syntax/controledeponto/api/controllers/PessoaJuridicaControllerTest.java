package br.com.syntax.controledeponto.api.controllers;

import br.com.syntax.controledeponto.FabricaDeModelsParaTeste;
import br.com.syntax.controledeponto.entities.Empresa;
import br.com.syntax.controledeponto.services.EmpresaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PessoaJuridicaControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private EmpresaService empresaService;

  private final String PESSOA_JURIDICA_ENDPOINT = "/api/pessoa-juridica";
  private final String ENDPOINT_CNPJ = "/cnpj/";
  private final String CNPJ = "19327916000189";

  private Empresa empresa;

  @Before
  public void setUp() {
    this.empresa = FabricaDeModelsParaTeste.criarEmpresa(CNPJ);
  }

  @Test
  @WithMockUser
  public void testConsultaPorCnpjValido() throws  Exception {

    String urlCnpj = PESSOA_JURIDICA_ENDPOINT + ENDPOINT_CNPJ;
    BDDMockito.given(this.empresaService.buscaPorCnpj(Mockito.anyString())).willReturn(Optional.of(this.empresa));

    mockMvc.perform(MockMvcRequestBuilders.get(urlCnpj + CNPJ).accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.result.id").value(this.empresa.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result.razaoSocial").value(this.empresa.getRazaoSocial()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result.cnpj").value(this.empresa.getCnpj()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.erros").isEmpty());

  }

  @Test
  @WithMockUser
  public void testConsultaPorCnpjInvalido() throws  Exception  {
    String urlCnpj = PESSOA_JURIDICA_ENDPOINT + ENDPOINT_CNPJ;
    BDDMockito.given(this.empresaService.buscaPorCnpj(Mockito.anyString())).willReturn(Optional.empty());
    mockMvc.perform(MockMvcRequestBuilders.get(urlCnpj + CNPJ).accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.erros").value("Nenhuma empresa encontrada para o CNPJ: "  + CNPJ));
  }

}
