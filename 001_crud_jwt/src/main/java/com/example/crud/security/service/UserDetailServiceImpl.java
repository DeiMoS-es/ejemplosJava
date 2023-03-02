package com.example.crud.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.crud.security.entity.Usuario;
import com.example.crud.security.entity.UsuarioPrincipal;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.getByNombre(nombreUsuario).get();//Añadimos .get() porque getByNombre devuelve un optional
		
		return UsuarioPrincipal.build(usuario);
	}

}
