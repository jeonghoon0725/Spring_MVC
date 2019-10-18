package com.dev.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@RequestMapping("memberInsert.do") //handler mapping
	public String insert(MemberVO member, Model model) { //회원가입 서비스 처리 controller, spring에서만 사용 가능
		service.memberInsert(member);
		model.addAttribute("id", member);
		return "memberInsertOutput";
	}
/*	public String insert(MemberVO member, HttpServletRequest req) { //회원가입 서비스 처리 controller, 표준
		service.memberInsert(member);
		req.setAttribute("id", member.getId());
		return "memberInsertOutput";
	}
*/
	@RequestMapping("memberList.do")
	public String list(Model model){
		List<MemberVO> list = service.memberList();
		model.addAttribute("list", list);
		return "memberListOutput";
	}
	
	@RequestMapping("searchInput.do")
	public String searchInput(){
		return "memberSearch";
	}
	@RequestMapping("updateInput.do")
	public String updateInput(){
		return "memberUpdate"; //새로 요청한 것처럼 
	}
	@RequestMapping("deleteInput.do")
	public String deleteInput(){
		return "memberDelete";
	}
	@RequestMapping("memberSearch.do")
	public String membersearch(RedirectAttributes ra, String id, String job){ 
		//RedirectAttributes	spring에서만 지원하는 api
		MemberVO member = service.memberSearch(id);
		ra.addFlashAttribute("member", member);
		String path = null;
		if(job.equals("search")){
			path = "redirect:/searchInput.do";
		} else if(job.equals("delete")){
			path = "redirect:/deleteInput.do";
		} else if(job.equals("update")){
			path = "redirect:/updateInput.do";
		}
		return path;
	}
	
	@RequestMapping("memberUpdate.do")
	public String memberUpdate(MemberVO member, Model model){
		service.memberUpdate(member);
		model.addAttribute("id", member.getId());
		return "memberUpdateOutput";
	}
	@RequestMapping("memberDelete.do")
	public String memberDelete(String id, Model model){
		service.memberDelete(id);
		return "memberDeleteOutput";
	}
}
