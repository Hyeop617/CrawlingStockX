package org.hyeop.crawlingstockx.domain.stockxsizelist;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hyeop.crawlingstockx.domain.TimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StockxSizeList extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String price;

    @Builder
    public StockxSizeList(Long id, String model, String size, String price) {
        this.id = id;
        this.model = model;
        this.size = size;
        this.price = price;
    }
}
