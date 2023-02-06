package com.catabaseScrape.scraper;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WikiScrape {
	private WebDriver driver;
	
	public void open() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get(WikiPOM.getUrl());
	}
	
	public List<String> getTableData() throws InterruptedException {
		List<String> dataList = new ArrayList<String>();
		
		WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[3]/main/div[2]/div[3]/div[1]/table[1]"));
		
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		
		for(int i = 1; i <= 2; i++) {
			List<WebElement> title = rows.get(i).findElements(By.tagName("th"));
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			
			System.out.println(title.get(0).getText() + " " + cols.get(0).getText() + " " + cols.get(1).getText() + " " + cols.get(2).getText() + " " + cols.get(3).getText() + " " + cols.get(4).getText());
			
			Thread.sleep(1000);
			driver.close();
		}
		
		return dataList;
	}
	
	public void close() {
		driver.close();
	}
}
