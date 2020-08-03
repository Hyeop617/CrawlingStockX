package org.hyeop.crawlingstockx.web.dto;


import lombok.*;
import org.hyeop.crawlingstockx.domain.sneakers.Sneakers;
import org.hyeop.crawlingstockx.domain.utils.SneakersPrice;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SneakersDto {
    String name;
    String imgPath;
//    List<SneakersPrice> priceList;

    public Sneakers toEntity(){
        return Sneakers.builder()
                .name(name)
                .imgPath(imgPath)
                .build();
    }
}
