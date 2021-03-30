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

import com.librarymgm.dto.BookDTO;

@Repository
public class BookDAOImpl implements BookDAOInt {

	private static Logger log = Logger.getLogger(BookDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(BookDTO dto) {
		log.info("BookDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("BookDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(BookDTO dto) {
		log.info("BookDAOImpl Delete method Start");
		Session session = entityManager.unwrap(Session.class);
		session.delete(dto);
		log.info("BookDAOImpl Delete method End");

	}

	@Override
	public BookDTO findBypk(long pk) {
		log.info("BookDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		BookDTO dto = (BookDTO) session.get(BookDTO.class, pk);
		log.info("BookDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public BookDTO findByName(String name) {
		log.info("BookDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(BookDTO.class);
		criteria.add(Restrictions.eq("name", name));
		BookDTO dto = (BookDTO) criteria.uniqueResult();
		log.info("BookDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public BookDTO findByBookCode(long code) {
		log.info("BookDAOImpl FindByBookCode method Start");
		Session session = entityManager.unwrap(Session.class);
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(BookDTO.class);
		criteria.add(Restrictions.eq("bookCode", code));
		BookDTO dto = (BookDTO) criteria.uniqueResult();
		log.info("BookDAOImpl FindByBookCode method End");
		return dto;
	}

	@Override
	public void update(BookDTO entity) {
		log.info("BookDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(entity);
		log.info("BookDAOImpl update method End");
	}

	@Override
	public List<BookDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<BookDTO> list(int pageNo, int pageSize) {
		log.info("BookDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<BookDTO> query = session.createQuery("from BookDTO", BookDTO.class);
		List<BookDTO> list = query.getResultList();
		log.info("BookDAOImpl List method End");
		return list;
	}

	@Override
	public List<BookDTO> search(BookDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<BookDTO> search(BookDTO dto, int pageNo, int pageSize) {
		log.info("BookDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from BookDTO as b where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and b.id = " + dto.getId());
			}
			if (dto.getBookCode() > 0) {
				hql.append("and b.bookCode = " + dto.getBookCode());
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				hql.append("and b.name like '%" + dto.getName() + "%'");
			}
			if (dto.getWriterName() != null && dto.getWriterName().length() > 0) {
				hql.append("and b.writerName like '%" + dto.getWriterName() + "%'");
			}
		}

		Query<BookDTO> query = session.createQuery(hql.toString(), BookDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<BookDTO> list = query.getResultList();
		log.info("BookDAOImpl Search method End");
		return list;
	}

	@Override
	public Blob getImageById(long id) throws SerialException, SQLException {

		Session session = entityManager.unwrap(Session.class);
		BookDTO Book = (BookDTO) session.get(BookDTO.class, id);
		byte[] blob = Book.getImage();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}

}
