package com.ehbrail;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.database.DataSource;
import com.database.TicketDAO;
import com.model.Ticket;

public class JobSendPersistentTickets implements Job{
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<Ticket> lijst = com.persistentie.TicketCSV.getTickets();
		if (DataSource.dbStatus == "ONLINE" && lijst.size() != 0){
			logger.info("Doorsturen gepersisteerde tickets.");
			for(Ticket t : lijst){
				if(TicketDAO.writeTicket(lijst.get(0)) == true){
					com.persistentie.TicketCSV.removeFirstTicket();
				}
			}
		}
	}

}
