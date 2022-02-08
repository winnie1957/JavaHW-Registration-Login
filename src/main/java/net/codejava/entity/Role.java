package net.codejava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long roleId;
	@Column(name = "role_name", nullable = false)
	private String roleName;
	
	public Role() {
	}
	public Role(Long roleId) {
		this.roleId = roleId;
	}
	public Role(String roleName) {
		this.roleName = roleName;
	}
	public Role(Long roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
	

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		return this.roleName;
	}
	
	
}
