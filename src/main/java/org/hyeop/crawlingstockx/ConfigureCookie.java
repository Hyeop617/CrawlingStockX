package org.hyeop.crawlingstockx;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.time.ZoneOffset;
import java.util.Date;
import java.time.LocalDateTime;

public class ConfigureCookie {

    public static WebDriver setCookie(WebDriver driver){
        LocalDateTime now = LocalDateTime.now();
        Date date = Date.from(now.toInstant(ZoneOffset.systemDefault().getRules().getOffset(now)));
        Date datePlusYear = Date.from(now.plusYears(1).toInstant(ZoneOffset.systemDefault().getRules().getOffset(now)));

        driver.manage().addCookie(new Cookie("stockx_dismiss_modal_expiration",now.plusYears(1).toString(),"stockx.com","/",datePlusYear));
        driver.manage().addCookie(new Cookie("stockx_dismiss_modal_set",now.toString(),"stockx.com","/",datePlusYear));
        driver.manage().addCookie(new Cookie("stockx_dismiss_modal","true","stockx.com","/",datePlusYear));
        driver.manage().addCookie(new Cookie("__abc","KR","stockx.com","/", null));
        driver.manage().addCookie(new Cookie("stockx_selected_region","KR","stockx.com","/", null));
        driver.manage().addCookie(new Cookie("stockx_selected_locale","en","stockx.com","/", null));
        return driver;
    }
}
