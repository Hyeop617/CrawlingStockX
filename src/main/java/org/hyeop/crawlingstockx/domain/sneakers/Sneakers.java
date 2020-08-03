package org.hyeop.crawlingstockx.domain.sneakers;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hyeop.crawlingstockx.domain.TimeEntity;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Sneakers extends TimeEntity {

    @Id
    private String model;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imgPath;

    @Builder
    public Sneakers(String model, String name, String imgPath) {
        this.model = model;
        this.name = name;
        this.imgPath = imgPath;
    }
}
