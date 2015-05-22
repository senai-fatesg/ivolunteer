package br.com.ambientinformatica.ivolunteer.util;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;

public class UsernamePasswordAuthenticationTokenCustom extends UsernamePasswordAuthenticationToken{

   private static final long serialVersionUID = 1L;

   public UsernamePasswordAuthenticationTokenCustom(Object principal,
         Object credentials, GrantedAuthority[] authorities) {
      super(principal, credentials, authorities);
   }
}

