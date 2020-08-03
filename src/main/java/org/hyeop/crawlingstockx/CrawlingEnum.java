package org.hyeop.crawlingstockx;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CrawlingEnum {
    BODY("//*[@id=\"products-container\"]/div[2]/div/div/div/a"),
    SIZE_BUTTON("//*[@id=\"market-summary\"]/div[1]/div/div/div[1]/button"),
    SNEAKERS_NAME("//*[@id=\"product-header\"]/div[1]/div/h1"),
    SNEAKERS_MODEL("//*[@id=\"product-header\"]/div[1]/div/small/div[2]/span"),
    SNEAKERS_IMAGE("//img[contains(@data-testid,'product-detail-image')]"),
    PRICE_LIST("//*[@id=\"market-summary\"]/div[1]/div/div/div[2]/div[2]/ul/li[not(contains(@class,'all'))]/div");

    private String xpath;


}
