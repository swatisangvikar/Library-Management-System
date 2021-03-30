package com.librarymgm.ctl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.librarymgm.exception.DuplicateRecordException;
import com.librarymgm.form.IssueBookForm;
import com.librarymgm.service.BookServiceInt;
import com.librarymgm.service.IssueBookServiceInt;
import com.librarymgm.service.UserServiceInt;
import com.librarymgm.util.DataUtility;

import com.librarymgm.dto.BookDTO;
import com.librarymgm.dto.IssueBookDTO;
import com.librarymgm.dto.UserDTO;





@Controller
@RequestMapping("/ctl/issue")
public class IssueBookCtl extends BaseCtl {

	@Autowired
	private IssueBookServiceInt service;
	
	@Autowired
	private UserServiceInt userService;
	
	@Autowired
	private BookServiceInt bookService;
	
	

	
	@GetMapping
	public String display(@RequestParam(required = false) Long id,Long bCode,@ModelAttribute("form") IssueBookForm form, Model model) {
		if (form.getId() > 0) {
			IssueBookDTO bean=service.findBypk(id);
			form.populate(bean);
		}if(DataUtility.getLong(String.valueOf(bCode))>0) {
			form.setbCode(String.valueOf(bCode));
		}
		
		return "issue";
	}
	
	
	
	@PostMapping
	public String submit(@Valid @ModelAttribute("form")  IssueBookForm form, BindingResult bindingResult,
			Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/issue";
		}
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			
				if (bindingResult.hasErrors()) {
					return "issue";
				}
				
				IssueBookDTO bean = (IssueBookDTO) form.getDto();
				UserDTO uDto=userService.findByLibCode(bean.getLibCode());
				BookDTO bDto=bookService.findByBookCode(bean.getBookCode());
				bean.setBookName(bDto.getName());
				bean.setWriterName(bDto.getWriterName());
				bean.setStudentName(uDto.getFirstName()+" "+uDto.getLastName());
				if(bean.getId()>0) {
					service.update(bean);
					model.addAttribute("success", "Book Issue update Successfully!!!!");
				}else {
				service.add(bean);
				model.addAttribute("success", "Book Issue Successfully!!!!");
				}
				return "issue";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "issue";
		}
		return "";
	}
	
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(HttpSession sesion,@ModelAttribute("form") IssueBookForm form,
			@RequestParam(required = false) String operation,HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/issue/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/issue";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					IssueBookDTO dto = new IssueBookDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success","Deleted Successfully!!!");
			} else {
				model.addAttribute("error","Select at least one record");
			}
		}
		IssueBookDTO dto=(IssueBookDTO)form.getDto();
		
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		if(uDto.getRoleId()==2) {
			dto.setLibCode(uDto.getLibraryCodel());
		}
		List<IssueBookDTO> list =service.search(dto, pageNo, pageSize);
		List<IssueBookDTO> totallist =service.search(dto);
		model.addAttribute("list", list);
		
		

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error","Record not found");
		}
		
		

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "issueList";
	}
	
}
