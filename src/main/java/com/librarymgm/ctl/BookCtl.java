package com.librarymgm.ctl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.librarymgm.exception.DuplicateRecordException;
import com.librarymgm.form.BookForm;
import com.librarymgm.service.BookServiceInt;

import com.librarymgm.dto.BookDTO;






@Controller
@RequestMapping("/ctl/book")
public class BookCtl extends BaseCtl {

	@Autowired
	private BookServiceInt service;
	
	
	/*
	 * @ModelAttribute public void preload(Model model) {
	 * 
	 * HashMap<String,String> map=new HashMap<String, String>();
	 * map.put("Full Time","Full Time"); map.put("Part Time","Part Time");
	 * model.addAttribute("type",map);
	 * 
	 * }
	 */
	
	@GetMapping
	public String display(@RequestParam(required = false) Long id,@ModelAttribute("form") BookForm form, Model model) {
		if (form.getId() > 0) {
			BookDTO bean=service.findBypk(id);
			form.populate(bean);
		}
		return "book";
	}
	
	
	
	@PostMapping
	public String submit(@RequestParam("image") MultipartFile file,HttpSession session,@Valid @ModelAttribute("form")  BookForm form, BindingResult bindingResult,
			Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/book";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			
				if (bindingResult.hasErrors()) {
					return "book";
				}
				
				BookDTO bean = (BookDTO) form.getDto();
				bean.setImage(file.getBytes());
				if(bean.getId()>0) {
					service.update(bean);
					model.addAttribute("success", "Book update Successfully!!!!");
				}else {
				service.add(bean);
				model.addAttribute("success", "Book Added Successfully!!!!");
				}
				return "book";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "book";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") BookForm form,
			@RequestParam(required = false) String operation,HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/book/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/book";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					BookDTO dto = new BookDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success","Deleted Successfully!!!");
			} else {
				model.addAttribute("error","Select at least one record");
			}
		}
		BookDTO dto=(BookDTO)form.getDto();
		List<BookDTO> list =service.search(dto, pageNo, pageSize);
		List<BookDTO> totallist =service.search(dto);
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
		return "bookList";
	}
	
	@GetMapping("/getImage/{id}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
		response.setContentType("image/jpeg");

		Blob blb=service.getImageById(id);
		
		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	
}
