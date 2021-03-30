package com.librarymgm.service;

import java.util.List;

import com.librarymgm.dto.IssueBookDTO;
import com.librarymgm.exception.DuplicateRecordException;

public interface IssueBookServiceInt {

	public long add(IssueBookDTO dto) throws DuplicateRecordException;

	public void delete(IssueBookDTO dto);

	public IssueBookDTO findBypk(long pk);

	public IssueBookDTO findByName(String name);

	public void update(IssueBookDTO dto) throws DuplicateRecordException;

	public List<IssueBookDTO> list();

	public List<IssueBookDTO> list(int pageNo, int pageSize);

	public List<IssueBookDTO> search(IssueBookDTO dto);

	public List<IssueBookDTO> search(IssueBookDTO dto, int pageNo, int pageSize);


}
