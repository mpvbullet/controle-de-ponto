package br.com.syntax.controledeponto.api.security;

import br.com.syntax.controledeponto.entities.Funcionario;
import br.com.syntax.controledeponto.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class FabricaDeUsuarioJwt {

  public static UsuarioJwt create(Funcionario funcionario) {
    return new UsuarioJwt(funcionario.getId(),
            funcionario.getEmail(), funcionario.getSenha(),
            mapToGrantedAuthorities(funcionario.getPerfil()));
  }

  /**
   * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
   *
   * @param perfilEnum
   * @return List<GrantedAuthority>
   */
  private static List<GrantedAuthority> mapToGrantedAuthorities(Perfil perfilEnum) {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
    return authorities;
  }
}
