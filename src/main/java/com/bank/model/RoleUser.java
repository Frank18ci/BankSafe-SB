package com.bank.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String tipo;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean estado;
	
	@OneToMany(mappedBy = "roleUser", fetch = FetchType.LAZY, targetEntity = User.class)
	private List<User> users;
}
