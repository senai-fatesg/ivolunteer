package br.com.ambientinformatica.ivolunteer.util;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;



public class UsernamePasswordAuthenticationTokenCustom extends UsernamePasswordAuthenticationToken{

	private static final long serialVersionUID = 1L;

    public UsernamePasswordAuthenticationTokenCustom(Object principal,
            Object credentials, List<GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}

