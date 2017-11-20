package com.minsx.ccs.core.model;

import java.util.Date;


public class CCSBucket {

    private String name;

    private CCSOwner ccsOwner;
    
    private String location;

    private Date creationDate;
    
    private String storageClass;

    private String extranetEndpoint;
    
    private String intranetEndpoint;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CCSOwner getCCSOwner() {
		return ccsOwner;
	}

	public void setCCSOwner(CCSOwner ccsOwner) {
		this.ccsOwner = ccsOwner;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getStorageClass() {
		return storageClass;
	}

	public void setStorageClass(String storageClass) {
		this.storageClass = storageClass;
	}

	public String getExtranetEndpoint() {
		return extranetEndpoint;
	}

	public void setExtranetEndpoint(String extranetEndpoint) {
		this.extranetEndpoint = extranetEndpoint;
	}

	public String getIntranetEndpoint() {
		return intranetEndpoint;
	}

	public void setIntranetEndpoint(String intranetEndpoint) {
		this.intranetEndpoint = intranetEndpoint;
	}
}
