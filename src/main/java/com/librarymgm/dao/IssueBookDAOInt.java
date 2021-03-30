package com.librarymgm.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.librarymgm.dto.IssueBookDTO;


public interface IssueBookDAOInt {

	public long add(IssueBookDTO dto);
	
	public void delete(IssueBookDTO dto);
	
	public IssueBookDTO findBypk(long pk);
	
	public IssueBookDTO findByName(String name);
	
	public void update(IssueBookDTO dto);
	
	public List<IssueBookDTO> list();
	
	public List<IssueBookDTO>list(int pageNo,int pageSize);
	
	public List<IssueBookDTO> search(IssueBookDTO dto);
	
	public List<IssueBookDTO> search(IssueBookDTO dto,int pageNo,int pageSize);
	
}
