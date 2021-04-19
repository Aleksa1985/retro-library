package org.lexsoft.library;

import io.dropwizard.db.DataSourceFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class Configuration extends io.dropwizard.Configuration  {
    
	@Valid
	@NotNull
	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();
	
	public DataSourceFactory getDatabaseConfiguration() {
		return database;
	}
}
