package br.com.syntax.controledeponto.api.controllers;

import br.com.syntax.controledeponto.api.dtos.LancamentoDto;
import br.com.syntax.controledeponto.api.response.Response;
import br.com.syntax.controledeponto.entities.Funcionario;
import br.com.syntax.controledeponto.entities.Lancamento;
import br.com.syntax.controledeponto.enums.TipoDeControle;
import br.com.syntax.controledeponto.services.FuncionarioService;
import br.com.syntax.controledeponto.services.LancamentoService;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Optional;

@RestController
@RequestMapping("/api/lancamento")
@CrossOrigin(origins = "*")
public class LancamentoController {
  private static Logger log = LoggerFactory.getLogger(LancamentoController.class);

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Autowired private LancamentoService lancamentoService;
  @Autowired private FuncionarioService funcionarioService;

  @GetMapping("funcionario/{funcionarioId}")
  public ResponseEntity<Response< Page<LancamentoDto> >> listaPorFuncionario(@PathVariable("funcionarioId") Long id,
                                                                          @RequestParam(value = "pag", defaultValue = "0") Integer page,
                                                                          @RequestParam(value = "ord", defaultValue = "id") String ord,
                                                                          @RequestParam(value = "dir", defaultValue = "DESC") String dir) {

    Response< Page<LancamentoDto> > response = new Response<>();
    PageRequest pageRequest = new PageRequest(page, 100, Sort.Direction.valueOf(dir), ord);

    Funcionario funcionario = new Funcionario();
    funcionario.setId(id);
    Page<Lancamento> lancamentos = this.lancamentoService.getByFuncionario(funcionario, pageRequest);
    response.setResult( lancamentos.map( lancamento -> this.converterParaLancamentoDto(lancamento) ) );

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity< Response<LancamentoDto> > listaPorId(@PathVariable("id") Long id) {
    log.debug("Buscando o lançamento pelo código: {}", id);
    Response<LancamentoDto> response = new Response<>();
    Optional<Lancamento> lancamentoOptional = this.lancamentoService.getById(id);

    if (!lancamentoOptional.isPresent()) {
      response.getErros().add("Lançamento não encontrado");
      return ResponseEntity.badRequest().body(response);
    }

    response.setResult( this.converterParaLancamentoDto(lancamentoOptional.get()) );
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity< Response<LancamentoDto> > gravar(@Valid @RequestBody LancamentoDto lancamentoDto,  BindingResult result) {
    Response<LancamentoDto> response = new Response<>();

    try {
      Lancamento lancamento = this.converterParaLancamento(lancamentoDto);
      Optional<Funcionario> funcionarioOptional = this.funcionarioService.buscaPorId(lancamentoDto.getFuncionarioId());

      if (!funcionarioOptional.isPresent()) {
        response.getErros().add("O funcionário não existe");
        return ResponseEntity.badRequest().body(response);
      }

      lancamento.setFuncionario(funcionarioOptional.get());
      this.lancamentoService.grava(lancamento);

      response.setResult( this.converterParaLancamentoDto(lancamento) );
      return ResponseEntity.ok(response);
    } catch(Exception e) {
      response.getErros().add(e.getMessage());
      return ResponseEntity.badRequest().body(response);
    }

  }


  private LancamentoDto converterParaLancamentoDto(Lancamento lancamento) {
    LancamentoDto lancamentoDto = new LancamentoDto();
    lancamentoDto.setId(lancamento.getId());
    lancamentoDto.setData(dateFormat.format(lancamento.getData()));
    lancamentoDto.setDescricao(lancamento.getDescricao());
    lancamentoDto.setLocalizacao(lancamento.getLocalizacao());
    lancamentoDto.setTipo(lancamento.getTipo().toString());
    lancamentoDto.setFuncionarioId(lancamento.getFuncionario().getId());
    return lancamentoDto;
  }

  private Lancamento converterParaLancamento(LancamentoDto lancamentoDto) throws Exception {
    Lancamento lancamento = new Lancamento();
    lancamento.setId(lancamentoDto.getId());
    lancamento.setData( dateFormat.parse(lancamentoDto.getData()) );
    lancamento.setDescricao(lancamentoDto.getDescricao());
    lancamento.setLocalizacao(lancamentoDto.getLocalizacao());
    if (!EnumUtils.isValidEnum(TipoDeControle.class, lancamentoDto.getTipo())) {
      throw new Exception("O tipo de controle informado não é válido");
    }
    lancamento.setTipo(TipoDeControle.valueOf(lancamentoDto.getTipo()));
    return lancamento;
  }


}
