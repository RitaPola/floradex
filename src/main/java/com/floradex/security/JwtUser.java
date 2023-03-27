package com.floradex.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.floradex.entity.Ruolo;
import com.floradex.entity.Utente;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class JwtUser implements UserDetails{
	
	private static final long serialVersionUID = -8715079258346192012L;
	private Utente utente;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){

		Set <Ruolo> ruoli = utente.getRuoli();
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Ruolo ruolo : ruoli) {
			if ( ruolo.isRuolo()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + ruolo.getNome()));				 
			} else {
				authorities.add(new SimpleGrantedAuthority(ruolo.getNome()));				 
			}
		}
		return authorities;
	}


	@Override
	public String getPassword() {
		return utente.getPassword();
	}

	@Override
	public String getUsername() {
		return utente.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
