package com.rbc.test.repository;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class AppConfigID implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String version;
	private String appCode;
	
	
	public AppConfigID() {
		
	}
	public AppConfigID(String version, String appCode) {
		super();
		this.version = version;
		this.appCode = appCode;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appCode == null) ? 0 : appCode.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		AppConfigID other = (AppConfigID) obj;
		if (appCode == null) {
			if (other.appCode != null)
				return false;
		} else if (!appCode.equals(other.appCode))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}
