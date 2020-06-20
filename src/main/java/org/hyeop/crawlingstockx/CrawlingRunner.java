package org.hyeop.crawlingstockx;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CrawlingRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String url = "https://www.stockx.com/sneakers";

        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--disable-popup-blocking");


        WebDriver driver = new FirefoxDriver(options);
        driver.get(url);
        ConfigureCookie.setCookie(driver);

        ArrayList<String> href = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"products-container\"]/div[2]/div/div/div/a"));
        elements.stream().limit(5).forEach(i -> href.add(i.getAttribute("href")));

        ArrayList<ArrayList<String>> arr = new ArrayList<>();

        href.stream().forEach(e -> {
            driver.get(e);
            WebElement button = driver.findElement(By.xpath("//*[@id=\"market-summary\"]/div[1]/div/div/div[1]/button"));
            button.click();

            List<WebElement> priceList = driver.findElements(By.xpath("//*[@id=\"market-summary\"]/div[1]/div/div/div[2]/div[2]/ul/li/div"));
            priceList.stream().forEach(i -> {
                String title = i.findElement(By.className("title")).getText();
                String subtitle = i.findElement(By.className("subtitle")).getText();
                ArrayList<String> array = new ArrayList<>();
                System.out.print(title + " " + subtitle + "     ");
                array.add(title);
                array.add(subtitle);
                arr.add(array);
            });
        });
        System.out.println(arr);

//        elements.stream().forEach(i -> {
//            System.out.println(i.getAttribute("href"));
//            driver.get(i.getAttribute("href"));
//            List<WebElement> priceList = driver.findElements(By.xpath("//*[@id=\"market-summary\"]/div[1]/div/div/div[2]/div[2]/ul/li/div"));
////            priceList.stream().forEach( e -> {
////                String title = e.findElement(By.className("title")).getText();
////                String subtitle = e.findElement(By.className("subtitle")).getText();
////                ArrayList<String> array = new ArrayList<>();
////                array.add(title);
////                array.add(subtitle);
////                arr.add(array);
////            });
//        });
//        driver.navigate().refresh();
//        System.out.println(arr);

//        System.out.println(driver.manage().getCookieNamed("stockx_dismiss_modal_expiration"));


//        System.out.println(driver.getPageSource());
//        System.out.println(driver.getPageSource());
    }
}
