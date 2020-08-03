package org.hyeop.crawlingstockx.service;

import org.hyeop.crawlingstockx.ConfigureCookie;
import org.hyeop.crawlingstockx.CrawlingEnum;
import org.hyeop.crawlingstockx.domain.sneakers.Sneakers;
import org.hyeop.crawlingstockx.domain.sneakers.SneakersRepository;
import org.hyeop.crawlingstockx.domain.stockxsizelist.StockxSizeListRepository;
import org.hyeop.crawlingstockx.domain.utils.SneakersPrice;
import org.hyeop.crawlingstockx.web.dto.SneakersDto;
import org.hyeop.crawlingstockx.web.dto.StockXSizeListDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Service
public class SneakersService {
    @Autowired
    SneakersRepository sneakersRepository;

    @Autowired
    StockxSizeListRepository stockxSizeListRepository;
    
    WebDriver driver;
    FirefoxOptions options;

    String url = "https://stockx.com/search/sneakers";

    public void setDriver(){
        options = new FirefoxOptions();
        options.addArguments("--headless");


        driver = new FirefoxDriver(options);
        driver.get(url);
        ConfigureCookie.setCookie(driver);
    }

    public List<SneakersDto> printSneakers(String keyword) {
        if (!keyword.isEmpty()) {
            url = "https://stockx.com/sneaker?s=" + keyword;
        }

        setDriver();

        List<String> href = driver.findElements(By.xpath(CrawlingEnum.BODY.getXpath()))
                .stream()
                .limit(4)
                .map(i -> i.getAttribute("href"))
                .collect(toList());

        return href.stream()
                .map(i -> {
                    driver.get(i);
                    WebElement button = driver.findElement(By.xpath(CrawlingEnum.SIZE_BUTTON.getXpath()));
                    button.click();
                    String name = driver.findElement(By.xpath(CrawlingEnum.SNEAKERS_NAME.getXpath())).getText();
                    String imgPath = driver.findElement(By.xpath(CrawlingEnum.SNEAKERS_IMAGE.getXpath())).getAttribute("src");
                    List<SneakersPrice> priceList = driver.findElements(By.xpath(CrawlingEnum.PRICE_LIST.getXpath()))
                            .stream()
                            .filter(isSizeMatch)
                            .map(e -> {
                                String size = e.findElement(By.className("title")).getText();
                                String price = e.findElement(By.className("subtitle")).getText();
                                return SneakersPrice
                                        .builder()
                                        .size(convertUStoCM(size))
                                        .price(price)
                                        .priceTax(claculateTax(price))
                                        .inputPrice("0")
                                        .difference("0")
                                        .build();
                            })
                            .collect(toList());
                    return SneakersDto.builder()
                            .name(name)
                            .imgPath(imgPath)
//                            .priceList(priceList)
                            .build();

                }).collect(toList());
    }

    public List<Long> updateSneakers(String keyword){
        if (!keyword.isEmpty()) {
            url = "https://stockx.com/sneaker?s=" + keyword;
        }
        setDriver();

        List<String> href = driver.findElements(By.xpath(CrawlingEnum.BODY.getXpath()))
                .stream()
                .limit(2)
                .map(i -> i.getAttribute("href"))
                .collect(toList());

        return href.stream()
                .map(i -> {
                    driver.get(i);
                    WebElement button = driver.findElement(By.xpath(CrawlingEnum.SIZE_BUTTON.getXpath()));
                    button.click();
                    String name = driver.findElement(By.xpath(CrawlingEnum.SNEAKERS_NAME.getXpath())).getText();
                    String model = driver.findElement(By.xpath(CrawlingEnum.SNEAKERS_MODEL.getXpath())).getText();
                    String imgPath = driver.findElement(By.xpath(CrawlingEnum.SNEAKERS_IMAGE.getXpath())).getAttribute("src");
                    Sneakers sneakers = sneakersRepository.findByModel(model);
                    if(sneakers.toString().isEmpty()){
                        Long sneakers_id = saveSneakers(SneakersDto.builder().name(name).imgPath(imgPath).build());
                    }
                    driver.findElements(By.xpath(CrawlingEnum.PRICE_LIST.getXpath()))
                            .stream()
                            .filter(isSizeMatch)
                            .forEach(e -> {
                                String size = e.findElement(By.className("title")).getText();
                                String price = e.findElement(By.className("subtitle")).getText();
                                saveStockxSizeList(StockXSizeListDto.builder().size(size).price(price).sneakers_id(sneakers_id).build());
                            });
                    return sneakers_id;
                })
                .collect(toList());
    }

    public Long saveSneakers(SneakersDto sneakersDto){
        return sneakersRepository.save(sneakersDto.toEntity()).getId();
    }

    public Long saveStockxSizeList(StockXSizeListDto dto) {
        return stockxSizeListRepository.save(dto.toEntity()).getId();
    }

    public Predicate<WebElement> isSizeMatch = i -> {
        String size = i.findElement(By.className("title")).getText();
        if(size.endsWith("W")){
            double sizeNumber = Double.parseDouble(size.substring(3, size.length()-1));
            return sizeNumber >= 5 && sizeNumber <= 9;
        }else{
            double sizeNumber = Double.parseDouble(size.substring(3, size.length()));
            return sizeNumber >= 7 && sizeNumber <= 12;
        }

    };

    public String convertUStoCM(String size){
        if(size.endsWith("W")){
            double sizeNumber = Double.parseDouble(size.substring(3, size.length() - 1));
            int sizeCM = (int) (220 + 10 * (sizeNumber - 5));
            return String.valueOf(sizeCM);
        }else{
            double sizeNumber = Double.parseDouble(size.substring(3, size.length()));
            int sizeCM = (int) (250 + 10 * (sizeNumber-7));
            return String.valueOf(sizeCM);
        }
    }

    public String claculateTax(String price){
//        String price = e.findElement(By.className("subtitle")).getText();
        int priceInt = Integer.parseInt(price.substring(1, price.length()));
        return priceInt > 200? String.format("%.2f",priceInt*1.243):String.valueOf(priceInt);
    }

}
