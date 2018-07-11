package br.com.syntax.controledeponto.api.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

  private T result;
  private List<String> erros;

  public T getRetorno() {
    return result;
  }

  public void setRetorno(T retorno) {
    this.result = retorno;
  }

  public List<String> getErros() {
    if (this.erros == null) {
      this.erros = new ArrayList<>();
    }
    return this.erros;
  }

  public void setErros(List<String> erros) {
    this.erros = erros;
  }

}
