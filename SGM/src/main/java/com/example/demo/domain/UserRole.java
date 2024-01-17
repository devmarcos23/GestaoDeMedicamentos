package com.example.demo.domain;
/*@Author https://github.com/devmarcos23*/
public enum UserRole {

	SuperAdministrador("SuperAdministrador"),
	Administrador("Administrador"),
    Usuario("Usuario");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
	 
	 
	 
}
