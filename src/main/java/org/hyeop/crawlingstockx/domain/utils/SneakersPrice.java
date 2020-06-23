package org.hyeop.crawlingstockx.domain.utils;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class SneakersPrice {
    String size;
    String price;

    @Override
    public String toString() {
        return "SneakersPrice{" +
                "size='" + size + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
