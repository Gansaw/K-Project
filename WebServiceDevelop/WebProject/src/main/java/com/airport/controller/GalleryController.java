package com.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.domain.Gallery;
import com.airport.domain.Member;
import com.airport.service.GalleryService;

@RestController
public class GalleryController {
	
	@Autowired
	public GalleryService galleryService;
	
	@GetMapping("/getGalleryList")
	public String getGalleryList(@ModelAttribute("회원") Member member, Model model, Gallery gallery) {		
		model.addAttribute("galleryList", galleryService.getGalleryList());
		return "getGalleryList";
	}
	
	@GetMapping("/getGallery")
	public String getGallery(@ModelAttribute("회원") Member member, Model model, Long id) {
		Gallery gallery = galleryService.getGallery(Gallery.builder().id(id).build());
		model.addAttribute("gallery", gallery);
		return "getGallery";

	}
	
	@PostMapping("/insertGallery")
	public String insertGallery(@ModelAttribute("회원") Member member, Gallery gallery) {
		if (member.getUsername()==null)
			return "redirect:login";
		
		return "insertGallery";
	}
	
	@PutMapping("/updateGallery")
	public String updateGallery(@ModelAttribute("회원") Member member, Gallery gallery) {
		if (member.getUsername()==null)
			return "redirect:login";
		
		galleryService.updateGallery(gallery);
		return "redirect:getGalleryList";
		
	}
	
	@DeleteMapping("/deleteGallery")
	public String deleteGallery(@ModelAttribute("회원") Member member, Gallery gallery) {
		if (member.getUsername()==null)
			return "redirect:login";
		
		galleryService.deleteGallery(gallery);
		return "redirect:getGalleryList";
		
	}

}
