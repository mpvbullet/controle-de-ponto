package br.com.syntax.controledeponto.api.controllers;

import java.math.BigDecimal;

public class PessoaFisicaDto {

  private Long id;
  private String nome;
  private String cpf;
  private String email;
  private String senha;

  private BigDecimal valorHora;
  private Integer quantidadeHorasTrabalhadasDia;
  private Integer quantidadeHorasAlmoco;

  private String cnpj;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public BigDecimal getValorHora() {
    return valorHora;
  }

  public void setValorHora(BigDecimal valorHora) {
    this.valorHora = valorHora;
  }

  public Integer getQuantidadeHorasTrabalhadasDia() {
    return quantidadeHorasTrabalhadasDia;
  }

  public void setQuantidadeHorasTrabalhadasDia(Integer quantidadeHorasTrabalhadasDia) {
    this.quantidadeHorasTrabalhadasDia = quantidadeHorasTrabalhadasDia;
  }

  public Integer getQuantidadeHorasAlmoco() {
    return quantidadeHorasAlmoco;
  }

  public void setQuantidadeHorasAlmoco(Integer quantidadeHorasAlmoco) {
    this.quantidadeHorasAlmoco = quantidadeHorasAlmoco;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }
}
