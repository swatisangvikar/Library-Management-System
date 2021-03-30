package com.librarymgm.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.librarymgm.dto.BookDTO;
import com.librarymgm.exception.DuplicateRecordException;

public interface BookServiceInt {

	public long add(BookDTO dto) throws DuplicateRecordException;

	public void delete(BookDTO dto);

	public BookDTO findBypk(long pk);

	public BookDTO findByName(String name);
	
	public BookDTO findByBookCode(long code);

	public void update(BookDTO dto) throws DuplicateRecordException;

	public List<BookDTO> list();

	public List<BookDTO> list(int pageNo, int pageSize);

	public List<BookDTO> search(BookDTO dto);

	public List<BookDTO> search(BookDTO dto, int pageNo, int pageSize);

	public Blob getImageById(long id) throws SerialException, SQLException;

}
