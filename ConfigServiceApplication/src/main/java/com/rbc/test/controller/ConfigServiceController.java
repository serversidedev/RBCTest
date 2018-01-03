package com.rbc.test.controller;


import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.test.repository.AppConfig;
import com.rbc.test.repository.AppConfigID;
import com.rbc.test.repository.AppConfigRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path="/api")
public class ConfigServiceController {
	
	@Autowired
	private AppConfigRepository appConfigRepository;

	@RequestMapping(value = "/{appCode}/config/{version}", method = RequestMethod.POST)
	public String addAppConfig(@RequestBody PropertyWrapper properties, @PathVariable(value = "appCode") String appCode,
			@PathVariable(value = "version") String version)
			throws FileNotFoundException, UnsupportedEncodingException {
		log.info("Adding properties file: ");

		StringBuffer buffer = new StringBuffer("");
		for (int i = 0; i < properties.getProperties().size(); i++) {
			log.info("Writing " + properties.getProperties().get(i));
			// writer.write(properties.getProperties().get(i));
			buffer.append(properties.getProperties().get(i));
			buffer.append("\n");
		}
		AppConfig appConfig = new AppConfig();
		appConfig.setId(new AppConfigID(version,appCode));
		//appConfig.setAppCode(appCode);
		//appConfig.setVersion(version);
		appConfig.setFile(buffer.toString().getBytes());
		appConfigRepository.save(appConfig);
		return "Record_Added";
	}
	@RequestMapping(value ="/{appCode}/config/{version}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AppConfig getAppConfig(@PathVariable(value = "appCode")String appCode,@PathVariable(value = "version")String version)
	{
		log.info("Searching for properties file");
		//AppConfig appConfig = appConfigRepository.findByAppCodeAndVersion(appCode, version);
		AppConfig appConfig = appConfigRepository.findById(new AppConfigID(version,appCode));
		String content = new String(appConfig.getFile());
		appConfig.setProperties(content);
		return appConfig;
	}
	@RequestMapping(value="/{appCode}/config", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AppConfig>listAppConfigVersions(@PathVariable(value="appCode")String appCode)
	{
		log.info("Listing properties files");
		List<AppConfig> lstAppConfig = appConfigRepository.findByIdAppCode(appCode);
		return lstAppConfig;
	}
	
}
