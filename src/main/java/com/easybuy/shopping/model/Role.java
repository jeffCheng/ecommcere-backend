package com.easybuy.shopping.model;

public enum Role {
	ADMIN("ROLE_ADMIN"), 
	PM("ROLE_PM"), 
	CUSTOMER("ROLE_CUSTOMER");
	
    private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
