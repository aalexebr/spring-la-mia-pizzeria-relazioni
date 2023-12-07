package org.java.spring.repo;

import java.util.List;

import org.java.spring.pojo.Pizza;
import org.java.spring.pojo.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Integer>{

	List <SpecialOffer> findByTitleContaining(String x);
}
