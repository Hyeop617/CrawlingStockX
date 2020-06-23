package org.hyeop.crawlingstockx.web.dto;


import lombok.*;
import org.hyeop.crawlingstockx.domain.utils.SneakersPrice;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SneakersDto {
    String title;
    String imgPath;
    List<SneakersPrice> priceList;
}
