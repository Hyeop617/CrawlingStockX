package org.hyeop.crawlingstockx.domain.sneakers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SneakersRepository extends JpaRepository<Sneakers, Integer> {

    public Sneakers findByModel(String model);

}
