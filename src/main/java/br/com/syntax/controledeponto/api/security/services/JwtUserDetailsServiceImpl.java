package br.com.syntax.controledeponto.api.security.services;

import java.util.Optional;

import br.com.syntax.controledeponto.api.security.FabricaDeUsuarioJwt;
import br.com.syntax.controledeponto.entities.Funcionario;
import br.com.syntax.controledeponto.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsServiceImpl implements CustomUserDetailsService {

	@Autowired
	private FuncionarioService funcionarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Funcionario> funcionario = funcionarioService.buscaPorEmail(username);

		if (funcionario.isPresent()) {
			return FabricaDeUsuarioJwt.create(funcionario.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}

}
