package br.com.rd.queroserdev.loja.mvc.model;


import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authorities {


	@OneToOne
	@JoinColumn(name = "username", referencedColumnName = "username", nullable = false) 
    private User username;
    
    private String authority;

	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
    
}
