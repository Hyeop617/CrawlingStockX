package org.hyeop.crawlingstockx.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hyeop.crawlingstockx.domain.stockxsizelist.StockxSizeList;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class StockXSizeListDto {
    private Long sneakers_id;
    private String size;
    private String price;

    public StockxSizeList toEntity(){
        return StockxSizeList.builder()
                .sneakers_id(sneakers_id)
                .size(size)
                .price(price)
                .build();
    }
}
