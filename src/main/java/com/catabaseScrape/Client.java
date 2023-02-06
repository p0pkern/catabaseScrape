package com.catabaseScrape;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.catabaseScrape.scraper.WikiScrape;

public class Client {
	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

	public static void main(String[] args) {
		WikiScrape wiki = new WikiScrape();
		
		try {
			wiki.open();
			LOGGER.info("Success");
			wiki.getTableData();
			LOGGER.info("Table Data Secured");
			wiki.close();
			LOGGER.info("Successfully closed application.");
		} catch (Exception e){
			LOGGER.info(e.getMessage());
		}
		

	}

}
