package org.java.spring.service;

import java.util.List;

import org.java.spring.pojo.SpecialOffer;
import org.java.spring.repo.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {
	
	@Autowired
	private SpecialOfferRepository specialOfferRepo;
	
	public List<SpecialOffer> findAll(){
		return specialOfferRepo.findAll();
	}
	
	public SpecialOffer findById(int id) {
		return specialOfferRepo.findById(id).get();
	}
	
	public void save(SpecialOffer specialOffer) {
		specialOfferRepo.save(specialOffer);
	}
	
	public List<SpecialOffer> findByTitle(String x){
		
		return specialOfferRepo.findByTitleContaining(x);
	}
	
	public void delete(SpecialOffer specialOffer) {
		
		specialOfferRepo.delete(specialOffer);
	}
	
	public void deleteById(int id) {
		
		specialOfferRepo.deleteById(id);
	}

}
