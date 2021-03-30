package com.librarymgm.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.librarymgm.dto.IssueBookDTO;




@Repository
public class IssueBookDAOImpl implements IssueBookDAOInt {

	private static Logger log = Logger.getLogger(IssueBookDAOImpl.class.getName());
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public long add(IssueBookDTO dto) {
		log.info("IssueBookDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("IssueBookDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(IssueBookDTO dto) {
		log.info("IssueBookDAOImpl Delete method Start");
		Session session = entityManager.unwrap(Session.class);
		session.delete(dto);
		log.info("IssueBookDAOImpl Delete method End");
		
	}

	@Override
	public IssueBookDTO findBypk(long pk) {
		log.info("IssueBookDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		IssueBookDTO dto = (IssueBookDTO) session.get(IssueBookDTO.class, pk);
		log.info("IssueBookDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public IssueBookDTO findByName(String name) {
		log.info("IssueBookDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(IssueBookDTO.class);
		criteria.add(Restrictions.eq("name",name));
		IssueBookDTO dto = (IssueBookDTO) criteria.uniqueResult();
		log.info("IssueBookDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(IssueBookDTO entity) {
		log.info("IssueBookDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(entity);
		log.info("IssueBookDAOImpl update method End");
	}

	@Override
	public List<IssueBookDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<IssueBookDTO> list(int pageNo, int pageSize) {
		log.info("IssueBookDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<IssueBookDTO> query = session.createQuery("from IssueBookDTO", IssueBookDTO.class);
		List<IssueBookDTO> list = query.getResultList();
		log.info("IssueBookDAOImpl List method End");
		return list;
	}

	@Override
	public List<IssueBookDTO> search(IssueBookDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<IssueBookDTO> search(IssueBookDTO dto, int pageNo, int pageSize) {
		log.info("IssueBookDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from IssueBookDTO as i where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and i.id = " + dto.getId());
			}
			if (dto.getBookCode() > 0) {
				hql.append("and i.bookCode = " + dto.getBookCode());
			}
			if (dto.getLibCode() > 0) {
				hql.append("and i.libCode = " + dto.getLibCode());
			}
		}
		Query<IssueBookDTO> query = session.createQuery(hql.toString(), IssueBookDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<IssueBookDTO> list = query.getResultList();
		log.info("IssueBookDAOImpl Search method End");
		return list;
	}

	
	

}
