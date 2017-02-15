package br.com.ambientinformatica.ivolunteer.util;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


public class UsuarioImpl extends User{
	private static final long serialVersionUID = 1L;
	   
	   private List<GrantedAuthority> authorities;

	   public UsuarioImpl(String username, String password, boolean enabled,
	         boolean accountNonExpired, boolean credentialsNonExpired,
	         boolean accountNonLocked, List<GrantedAuthority> authorities) {
	      super(username, password, enabled, accountNonExpired, credentialsNonExpired,
	            accountNonLocked, authorities);
	      this.authorities = authorities;
	   }

	   public void addAuthoritys(List<GrantedAuthority> authorities){
	      this.authorities.addAll(authorities);
	      UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	      UsernamePasswordAuthenticationTokenCustom authenticationTokenCustom = new UsernamePasswordAuthenticationTokenCustom(authentication.getPrincipal(), authentication.getCredentials(), this.authorities);
	      SecurityContextHolder.getContext().setAuthentication(authenticationTokenCustom);
	   }

	   @Override
	   public List<GrantedAuthority> getAuthorities() {
	      return this.authorities;
	   }
}

