package com.psoft.scrumboard.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.psoft.scrumboard.model.Usuario;

@Repository
public class UsuarioRepository {
	
	private Map<String, Usuario> usuarios;
	
	public UsuarioRepository() {
		this.usuarios = new HashMap<String, Usuario>();
	}
	
	public void addUser(Usuario usuario) {
		this.usuarios.put(usuario.getUsername(), usuario);
	}
	
	public boolean containsUsername(String username) {
		return this.usuarios.containsKey(username);
	}
	
	public Usuario getUser(String username) {
		return this.usuarios.get(username);
	}
	
	public void delUser(String username) {
		this.usuarios.remove(username);
	}
	
	public void updateUser(Usuario usuario) {
		this.usuarios.replace(usuario.getUsername(), usuario);
	}

}
