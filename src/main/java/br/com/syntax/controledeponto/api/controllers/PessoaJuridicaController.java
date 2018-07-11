package br.com.syntax.controledeponto.api.controllers;

import br.com.syntax.controledeponto.api.dtos.PessoaJuridicaDto;
import br.com.syntax.controledeponto.api.response.Response;
import br.com.syntax.controledeponto.entities.Empresa;
import br.com.syntax.controledeponto.services.EmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pessoa-juridica")
@CrossOrigin(origins = "*")
public class PessoaJuridicaController {

  private static Logger log = LoggerFactory.getLogger(PessoaFisicaController.class);

  @Autowired private EmpresaService empresaService;

  @GetMapping("cnpj/{cnpj}")
  public ResponseEntity<Response<PessoaJuridicaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {
    Response<PessoaJuridicaDto> response = new Response<>();
    Optional<Empresa> empresa = this.empresaService.buscaPorCnpj(cnpj);

    if (!empresa.isPresent()) {
      response.getErros().add("Nenhuma empresa encontrada para o CNPJ: " + cnpj);
      return ResponseEntity.badRequest().body(response);
    }

    response.setRetorno( this.converterEmpresaParaDto(empresa.get()) );
    return ResponseEntity.ok(response);
  }

  private PessoaJuridicaDto converterEmpresaParaDto(Empresa empresa) {
    PessoaJuridicaDto pessoaJuridicaDto = new PessoaJuridicaDto();
    pessoaJuridicaDto.setId(empresa.getId());
    pessoaJuridicaDto.setRazaoSocial(empresa.getRazaoSocial());
    pessoaJuridicaDto.setCnpj(empresa.getCnpj());
    return pessoaJuridicaDto;
  }

}
