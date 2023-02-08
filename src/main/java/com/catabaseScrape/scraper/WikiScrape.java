package com.catabaseScrape.scraper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

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

		for (int i = 1; i <= rows.size(); i++) {
			List<WebElement> title = rows.get(i).findElements(By.tagName("th"));
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			String breed = title.get(0).getText();
			String location = cols.get(0).getText();
			String breedType = cols.get(1).getText();
			String bodyType = cols.get(2).getText();
			String coatType = cols.get(3).getText();
			String coatPattern = cols.get(4).getText();
			String url = "";

			try {
				if(cols.get(5).getText().trim() != "") {
					
					Thread.sleep(1000);
					
					cols.get(5).click();

					Thread.sleep(1000);

					url = driver.getCurrentUrl();

					Thread.sleep(1000);
				}
				
			} catch (Exception e) {

			}
			

			String query = "INSERT INTO cat (id, breed, location, breed_type, body_type, coat_type, coat_pattern, image_location) VALUES ("
					+ i + ", " + breed + "," + location + ", " + breedType + ", " + bodyType + ", " + coatType + ", "
					+ coatPattern + ", " + url + ")";

			System.out.println(query);
			
			if(url != "") {
				driver.navigate().back();
			}

		}

		return dataList;
	}

	public void close() {
		driver.close();
	}
}
