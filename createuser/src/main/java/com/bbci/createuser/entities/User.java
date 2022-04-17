package com.bbci.createuser.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
	private Long id;
	@Column(name = "uuid", unique = true)
	private String uuid;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "created")
	private Date created;
	@Column(name = "lastLogin")
	private Date lastLogin;
	@Column(name = "token")
	private String token;
	@Column(name = "isActivo")
	private Boolean isActivo;
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idUser", referencedColumnName = "id")
	private List<Phone> phones;

}
