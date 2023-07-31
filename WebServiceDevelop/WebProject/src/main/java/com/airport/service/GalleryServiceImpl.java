package com.airport.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.domain.Gallery;
import com.airport.persistence.GalleryRepo;

@Service
public class GalleryServiceImpl implements GalleryService {
	
	@Autowired
	public GalleryRepo galleryRepo;
	
	@Override
	public List<Gallery> getGalleryList() {
		return galleryRepo.findAll();
	}
	
	
	@Override
	public Gallery getGallery(Gallery gallery) {
		Gallery clicktime = galleryRepo.findById(gallery.getId()).get();
		clicktime.setView(clicktime.getView() + 1);
		galleryRepo.save(clicktime);
		return clicktime;
	}

	@Override
	public void insertGallery(Gallery gallery) {
		gallery.setId(0L);
		gallery.setDate(new Date());
		galleryRepo.save(gallery);
	}
	
	@Override
	public void updateGallery(Gallery gallery) {	
		Gallery findgallery = galleryRepo.findById(gallery.getId()).get();
		findgallery.setTitle(gallery.getTitle());
		findgallery.setContent(gallery.getContent());
		findgallery.setImageloc(gallery.getImageloc());
		galleryRepo.save(findgallery);
	}

	@Override
	public void deleteGallery(Gallery gallery) {
		galleryRepo.deleteById(gallery.getId());
	}
}
