package br.com.syntax.controledeponto.api.dtos;

import br.com.syntax.controledeponto.enums.TipoDeControle;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class LancamentoDto {

  private Long id;
  private String descricao;
  private String localizacao;
  private String data;
  private String tipo;
  private Long funcionarioId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getLocalizacao() {
    return localizacao;
  }

  public void setLocalizacao(String localizacao) {
    this.localizacao = localizacao;
  }

  @NotEmpty(message = "A data não pode ser vazia")
  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Long getFuncionarioId() {
    return funcionarioId;
  }

  public void setFuncionarioId(Long funcionarioId) {
    this.funcionarioId = funcionarioId;
  }
}
