package com.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.airport.domain.Member;
import com.airport.domain.Notice;
import com.airport.persistence.NoticeRepo;
import com.airport.service.NoticeService;

@RestController
public class NoticeController {
	
	@Autowired
	public NoticeService noticeService;
	
	@Autowired
	private NoticeRepo noticeRepo;
	
	@GetMapping("/getNoticeList")
	public String getNoticeList(@ModelAttribute("회원") Member member, Model model, Notice notice) {				
		model.addAttribute("noticeList", noticeService.getNoticeList());
		return "getNoticeList";
	}
	
    @GetMapping("/getNotice")
    @ResponseBody
    public Notice getNotice(@ModelAttribute("회원") Member member, Model model, @RequestParam Long id) {				
        Notice notice = noticeService.getNotice(Notice.builder().id(id).build());
        return notice;
    }

	
	@PostMapping("/insertNotice")
	public String insertNotice(@ModelAttribute("관리자") Member member, Notice notice) {
		if (member.getUsername()!="admin")
			return "관리자만 사용할 수 있는 기능입니다.";	
		
		return "insertNotice";
	}
	
	@PutMapping("/updateNotice")
	public String updateNotice(@ModelAttribute("관리자") Member member, Notice notice) {
		if (member.getUsername()!="admin")
			return "관리자만 사용할 수 있는 기능입니다.";	
		
		noticeService.updateNotice(notice);
		return "getNoticeList";
		
	}
	
	@DeleteMapping("/deleteNotice")
	public String deleteNotice(@ModelAttribute("회원") Member member, Notice notice) {
		if (member.getUsername()!="admin")
			return "관리자만 사용할 수 있는 기능입니다.";
		
		noticeService.deleteNotice(notice);
		return "getNoticeList";
		
	}
	
	@RequestMapping("/notices")
	public Iterable<Notice> getNotices(){
		return noticeRepo.findAll();
	}

}
