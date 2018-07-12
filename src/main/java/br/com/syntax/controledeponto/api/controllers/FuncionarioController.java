package br.com.syntax.controledeponto.api.controllers;

import br.com.syntax.controledeponto.services.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin(origins = "*")
public class FuncionarioController {
  private static Logger log = LoggerFactory.getLogger(FuncionarioController.class);
  @Autowired
  private FuncionarioService funcionarioService;


}
