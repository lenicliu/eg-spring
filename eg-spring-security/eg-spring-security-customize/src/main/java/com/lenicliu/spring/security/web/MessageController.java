package com.lenicliu.spring.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenicliu.spring.security.domain.Message;
import com.lenicliu.spring.security.service.MessageService;

@Controller
@RequestMapping("/messages")
public class MessageController extends WebController {

	@Autowired
	private MessageService messageService;

	@RequestMapping
	public String list(Model model, String keyword) {
		model.addAttribute("messages", messageService.findList(keyword));
		model.addAttribute("keyword", keyword);
		return "message/list";
	}

	@RequestMapping("/more")
	// http.authorizeRequests().antMatchers("/messages/more").hasAuthority("MSG_WRITE");
	@PreAuthorize("hasAuthority('MSG_WRITE')")
	public String more(Model model, String keyword) {
		model.addAttribute("messages", messageService.findList(keyword));
		model.addAttribute("keyword", keyword);
		model.addAttribute("more", "More");
		return "message/list";
	}

	@RequestMapping("/input")
	public String input(Model model, Long id) {
		model.addAttribute("message", messageService.findById(id));
		return "message/input";
	}

	@RequestMapping("/view")
	public String view(Model model, Long id) {
		model.addAttribute("readonly", "readonly");
		model.addAttribute("disabled", "disabled");
		return input(model, id);
	}

	@RequestMapping("/submit")
	public String submit(Message message) throws Exception {
		message.setUid(getCurrentUserDetails().getUser().getId());
		if (message.getId() != null && message.getId() > 0) {
			messageService.updateMessage(message);
		} else {
			messageService.createMessage(message);
		}
		return "redirect:";
	}

	@RequestMapping("/delete")
	public String delete(Long id) {
		messageService.deleteMessage(id);
		return "redirect:";
	}
}