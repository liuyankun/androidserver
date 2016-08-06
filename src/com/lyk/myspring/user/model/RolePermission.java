package com.lyk.myspring.user.model;

public class RolePermission {
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    private Role role;
    
	private Perminssion perminssion;
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Perminssion getPerminssion() {
		return perminssion;
	}

	public void setPerminssion(Perminssion perminssion) {
		this.perminssion = perminssion;
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}