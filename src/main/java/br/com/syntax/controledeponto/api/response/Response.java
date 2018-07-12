package br.com.syntax.controledeponto.api.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

  private T result;
  private List<String> erros;

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
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
