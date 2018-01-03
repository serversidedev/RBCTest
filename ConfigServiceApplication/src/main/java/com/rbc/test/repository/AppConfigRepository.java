package com.rbc.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AppConfigRepository extends CrudRepository<AppConfig,Long> {
	
	public AppConfig findById(AppConfigID id);
	public List<AppConfig> findByIdAppCode(String appCode);
	

}
