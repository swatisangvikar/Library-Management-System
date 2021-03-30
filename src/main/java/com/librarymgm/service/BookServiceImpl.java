package com.librarymgm.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.librarymgm.dao.BookDAOInt;
import com.librarymgm.dto.BookDTO;
import com.librarymgm.exception.DuplicateRecordException;




@Service
public class BookServiceImpl implements BookServiceInt {

	private static Logger log=Logger.getLogger(BookServiceImpl.class.getName());
	
	@Autowired
	private BookDAOInt dao;
	
	@Override
	@Transactional
	public long add(BookDTO dto) throws DuplicateRecordException {
		log.info("BookServiceImpl Add method start");
		BookDTO existDTO=dao.findByName(dto.getName());
		if(existDTO !=null)
			throw new DuplicateRecordException("Book Already Exist");
		long pk=dao.add(dto);
		log.info("BookServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(BookDTO dto) {
		log.info("BookServiceImpl Delete method start");
		dao.delete(dto);
		log.info("BookServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public BookDTO findBypk(long pk) {
		log.info("BookServiceImpl findBypk method start");
		BookDTO dto=dao.findBypk(pk);
		log.info("BookServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public BookDTO findByName(String name) {
		log.info("BookServiceImpl findByBookName method start");
		BookDTO dto=dao.findByName(name);
		log.info("BookServiceImpl findByBookName method end");
		return dto;
	}
	
	@Override
	@Transactional
	public BookDTO findByBookCode(long code) {
		log.info("BookServiceImpl findByBookCode method start");
		BookDTO dto=dao.findByBookCode(code);
		log.info("BookServiceImpl findByBookCode method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(BookDTO dto) throws DuplicateRecordException {
		log.info("BookServiceImpl update method start");
		BookDTO existDTO=dao.findByName(dto.getName());
		if(existDTO !=null && dto.getId()!=existDTO.getId())
			throw new DuplicateRecordException("Book Already Exist");
		dao.update(dto);
		log.info("BookServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<BookDTO> list() {
		log.info("BookServiceImpl list method start");
		List<BookDTO> list=dao.list();
		log.info("BookServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<BookDTO> list(int pageNo, int pageSize) {
		log.info("BookServiceImpl list method start");
		List<BookDTO> list=dao.list(pageNo, pageSize);
		log.info("BookServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<BookDTO> search(BookDTO dto) {
		log.info("BookServiceImpl search method start");
		List<BookDTO> list=dao.search(dto);
		log.info("BookServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<BookDTO> search(BookDTO dto, int pageNo, int pageSize) {
		log.info("BookServiceImpl search method start");
		List<BookDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("BookServiceImpl search method end");
		return list;
	}

	
	

	
	@Override
	@Transactional
	public Blob getImageById(long id) throws SerialException, SQLException {
		return dao.getImageById(id);
	}

	
}
