package com.ehbrail;

import org.apache.logging.log4j.LogManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.database.DataSource;
import com.persistentie.Cache;
public class JobPopulateCache implements Job{
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		if(Cache.isBinnengehaald() == false && DataSource.dbStatus == "ONLINE"){
			SoftwareProject.cache = new Cache();
			logger.info("Initialisatie cache.");
		}
		logger.info("Cache is al geïnitialiseerd");
	}
}
