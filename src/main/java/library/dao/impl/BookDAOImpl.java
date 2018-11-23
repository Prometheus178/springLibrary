package library.dao.interfaces.impl;

import library.entities.AuthorEntity;
import library.entities.BookEntity;
import library.entities.GenreEntity;
import library.dao.interfaces.BookDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component

public class BookDAOImpl implements BookDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private List<BookEntity> books;

    @Transactional
    @Override
    public List<BookEntity> getBooks() {
        books = sessionFactory.getCurrentSession()
                .createCriteria(BookEntity.class)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        System.out.println(books);
        return books;
    }

    @Override
    public List<BookEntity> getBooks(AuthorEntity author) {
        return null;
    }

    @Override
    public List<BookEntity> getBooks(String bookName) {
        return null;
    }

    @Override
    public List<BookEntity> getBooks(GenreEntity genre) {
        return null;
    }

    @Override
    public List<BookEntity> getBooks(Character letters) {
        return null;
    }
}
