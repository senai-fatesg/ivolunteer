package br.com.ambientinformatica.ivolunteer.util;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.User;

public class UsuarioImpl extends User{

   private static final long serialVersionUID = 1L;
   
   private GrantedAuthority[] authorities;
   
   public UsuarioImpl(String username, String password, boolean enabled,
         boolean accountNonExpired, boolean credentialsNonExpired,
         boolean accountNonLocked, GrantedAuthority[] authorities) {
      super(username, password, enabled, accountNonExpired, credentialsNonExpired,
            accountNonLocked, authorities);
      this.authorities = authorities.clone();
   }
   
   public void addAuthoritys(GrantedAuthority [] authorities){
      GrantedAuthority[] authoritiesOld = this.authorities;
      this.authorities = new GrantedAuthority[authoritiesOld.length + authorities.length];
      for (int i = 0; i < authoritiesOld.length; i++) {
         this.authorities[i] = authoritiesOld[i];
      }
      for (int i = authoritiesOld.length; i < this.authorities.length; i++) {
         this.authorities[i] = authorities[i - authoritiesOld.length];
      }
      UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
      UsernamePasswordAuthenticationTokenCustom authenticationTokenCustom = new UsernamePasswordAuthenticationTokenCustom(authentication.getPrincipal(), authentication.getCredentials(), this.authorities);
      SecurityContextHolder.getContext().setAuthentication(authenticationTokenCustom);
   }
   
   @Override
   public GrantedAuthority[] getAuthorities() {
      return this.authorities;
   }
   
}

