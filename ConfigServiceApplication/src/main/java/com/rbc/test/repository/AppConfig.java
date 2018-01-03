package com.rbc.test.repository;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppConfig {
	
	
	//private String version;
	//applications are unique in this system. It makes the most sense.
	@Id
	private AppConfigID id;
	///private String appCode;
	private byte[] file;
	private String properties;
	
	
	public AppConfigID getId() {
		return id;
	}

	public void setId(AppConfigID id) {
		this.id = id;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] bs) {
		this.file = bs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppConfig other = (AppConfig) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
