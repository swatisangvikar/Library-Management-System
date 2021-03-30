package com.librarymgm.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.librarymgm.dao.IssueBookDAOInt;
import com.librarymgm.dto.IssueBookDTO;
import com.librarymgm.exception.DuplicateRecordException;




@Service
public class IssueBookServiceImpl implements IssueBookServiceInt {

	private static Logger log=Logger.getLogger(IssueBookServiceImpl.class.getName());
	
	@Autowired
	private IssueBookDAOInt dao;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Override
	@Transactional
	public long add(IssueBookDTO dto) throws DuplicateRecordException {
		log.info("IssueBookServiceImpl Add method start");
		/*
		 * IssueBookDTO existDTO=dao.findByName(dto.getName()); if(existDTO !=null)
		 * throw new DuplicateRecordException("IssueBook Already Exist");
		 */
		long pk=dao.add(dto);
		log.info("IssueBookServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(IssueBookDTO dto) {
		log.info("IssueBookServiceImpl Delete method start");
		dao.delete(dto);
		log.info("IssueBookServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public IssueBookDTO findBypk(long pk) {
		log.info("IssueBookServiceImpl findBypk method start");
		IssueBookDTO dto=dao.findBypk(pk);
		log.info("IssueBookServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public IssueBookDTO findByName(String name) {
		log.info("IssueBookServiceImpl findByIssueBookName method start");
		IssueBookDTO dto=dao.findByName(name);
		log.info("IssueBookServiceImpl findByIssueBookName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(IssueBookDTO dto) throws DuplicateRecordException {
		log.info("IssueBookServiceImpl update method start");
		/*
		 * IssueBookDTO existDTO=dao.findByName(dto.getName()); if(existDTO !=null &&
		 * dto.getId()!=existDTO.getId()) throw new
		 * DuplicateRecordException("IssueBook Already Exist");
		 */
		dao.update(dto);
		log.info("IssueBookServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<IssueBookDTO> list() {
		log.info("IssueBookServiceImpl list method start");
		List<IssueBookDTO> list=dao.list();
		log.info("IssueBookServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<IssueBookDTO> list(int pageNo, int pageSize) {
		log.info("IssueBookServiceImpl list method start");
		List<IssueBookDTO> list=dao.list(pageNo, pageSize);
		log.info("IssueBookServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<IssueBookDTO> search(IssueBookDTO dto) {
		log.info("IssueBookServiceImpl search method start");
		List<IssueBookDTO> list=dao.search(dto);
		log.info("IssueBookServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<IssueBookDTO> search(IssueBookDTO dto, int pageNo, int pageSize) {
		log.info("IssueBookServiceImpl search method start");
		List<IssueBookDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("IssueBookServiceImpl search method end");
		return list;
	}

	
	



	
}
