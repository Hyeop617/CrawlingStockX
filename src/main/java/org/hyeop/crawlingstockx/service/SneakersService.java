package org.hyeop.crawlingstockx.service;

import org.hyeop.crawlingstockx.ConfigureCookie;
import org.hyeop.crawlingstockx.domain.utils.SneakersPrice;
import org.hyeop.crawlingstockx.web.dto.SneakersDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Service
public class SneakersService {

    public List<SneakersDto> printSneakers() {
        String url = "https://www.stockx.com/sneakers";

        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--disable-popup-blocking");


        WebDriver driver = new FirefoxDriver(options);
        driver.get(url);
        ConfigureCookie.setCookie(driver);

        List<String> href = driver.findElements(By.xpath("//*[@id=\"products-container\"]/div[2]/div/div/div/a"))
                .stream()
                .limit(1)
                .map(i -> i.getAttribute("href"))
                .collect(toList());

        return href.stream()
                .map(i -> {
                    driver.get(i);
                    WebElement button = driver.findElement(By.xpath("//*[@id=\"market-summary\"]/div[1]/div/div/div[1]/button"));
                    button.click();
                    String title = driver.findElement(By.xpath("//*[@id=\"product-header\"]/div[1]/div/h1")).getText();
                    String imgPath = driver.findElement(By.xpath("//*[@id=\"product-page\"]/div[1]/div[2]/div[1]/img")).getAttribute("src");
                    List<SneakersPrice> priceList = driver.findElements(By.xpath("//*[@id=\"market-summary\"]/div[1]/div/div/div[2]/div[2]/ul/li[not(contains(@class,'all'))]/div"))
                            .stream()
                            .filter(isSizeMatch)
                            .map(e -> SneakersPrice
                                    .builder()
                                    .size(convertUStoCM(e.findElement(By.className("title")).getText()))
                                    .price(e.findElement(By.className("subtitle")).getText())
                                    .build())
                            .collect(toList());
                    return SneakersDto.builder()
                            .title(title)
                            .imgPath(imgPath)
                            .priceList(priceList)
                            .build();

                }).collect(toList());
    }

    public Predicate<WebElement> isSizeMatch = i -> {
        String size = i.findElement(By.className("title")).getText();
        double sizeNumber = Double.parseDouble(size.substring(3, size.length()));
        return sizeNumber >= 7 && sizeNumber <= 12;
    };

    public String convertUStoCM(String size){
        double sizeNumber = Double.parseDouble(size.substring(3, size.length()));
        double sizeCM = 250 + 10 * (sizeNumber-7);
        return String.valueOf(sizeCM);
    }

}
