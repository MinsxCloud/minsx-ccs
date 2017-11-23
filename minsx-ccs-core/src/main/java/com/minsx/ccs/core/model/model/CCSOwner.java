package com.minsx.ccs.core.model.model;

import java.io.Serializable;

public class CCSOwner implements Serializable {

	private static final long serialVersionUID = 7335908891096674755L;
	
	private String id;
	private String displayName;
	
	public CCSOwner(String id, String displayName) {
		this.id=id;
		this.displayName=displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
