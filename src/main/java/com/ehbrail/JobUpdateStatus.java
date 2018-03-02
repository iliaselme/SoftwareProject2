package com.ehbrail;

import org.apache.logging.log4j.LogManager;
import org.jfree.util.Log;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleScheduleBuilder;

import org.quartz.Trigger;
import org.quartz.TriggerBuilder;


import com.database.DataSource;

public class JobUpdateStatus implements Job{
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//test databaseconnectie, update variabele en update UI label text
		DataSource.testConn();
		Log.info("dbStatus updated: - " + DataSource.dbStatus);
	}

}
