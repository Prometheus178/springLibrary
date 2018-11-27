package library.dao.impl;

import library.entities.AuthorEntity;
import library.entities.BookEntity;
import library.entities.GenreEntity;
import library.dao.interfaces.BookDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component

public class BookDAOImpl implements BookDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private ProjectionList bookProjection;

    public BookDAOImpl(){
        bookProjection = Projections.projectionList();
        bookProjection.add(Projections.property("id"),"id");
        bookProjection.add(Projections.property("name"), "name");
        bookProjection.add(Projections.property("image"), "image");
        bookProjection.add(Projections.property("genre"), "genre");
        bookProjection.add(Projections.property("pageCount"), "pageCount");
        bookProjection.add(Projections.property("isbn"), "isbn");
        bookProjection.add(Projections.property("publisher"), "publisher");
        bookProjection.add(Projections.property("author"), "author");
        bookProjection.add(Projections.property("publishYear"), "publishYear");
        bookProjection.add(Projections.property("descr"), "descr");
        bookProjection.add(Projections.property("rating"), "rating");
        bookProjection.add(Projections.property("voteCount"), "voteCount");
    }


    @Transactional
    @Override
    public List<BookEntity> getBooks() {


        List<BookEntity> books = createBookList(createBookCriteria());
        return books;
    }
    @Transactional
    @Override
    public List<BookEntity> getBooks(AuthorEntity author) {
        List<BookEntity> books = createBookList(createBookCriteria().add(Restrictions.ilike("author.fio",author.getFio(), MatchMode.ANYWHERE)));
        return books;
    }
    @Transactional
    @Override
    public List<BookEntity> getBooks(String bookName) {
        List<BookEntity> books = createBookList(createBookCriteria().add(Restrictions.ilike("b.name", bookName, MatchMode.ANYWHERE)));
        return books;
    }
    @Transactional
    @Override
    public List<BookEntity> getBooks(GenreEntity genre) {
        List<BookEntity> books = createBookList(createBookCriteria().add(Restrictions.ilike("author.fio", genre.getName(), MatchMode.ANYWHERE)));
        return books;
    }
    @Transactional
    @Override
    public List<BookEntity> getBooks(Character letters) {
        List<BookEntity> books = createBookList(createBookCriteria().add(Restrictions.ilike("b.name", letters.toString(), MatchMode.START)));
        return books;
    }

    private DetachedCriteria createBookCriteria(){
        DetachedCriteria bookListCriteria = DetachedCriteria.forClass(BookEntity.class, "b");
        createAliases(bookListCriteria);
        return bookListCriteria;
    }

    @Transactional

    public Object getFieldValue(Long id, String fieldName) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BookEntity.class);
        criteria.setProjection(Property.forName(fieldName));
        criteria.add(Restrictions.eq("id", id));
        return criteria.uniqueResult();

    }

    private void createAliases(DetachedCriteria criteria) {
        criteria.createAlias("b.author", "author");
        criteria.createAlias("b.genre", "genre");
        criteria.createAlias("b.publisher", "publisher");
    }


    private List<BookEntity> createBookList(DetachedCriteria bookListCriteria) {
        Criteria criteria = bookListCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        criteria.addOrder(Order.asc("b.name")).setProjection(bookProjection).setResultTransformer(Transformers.aliasToBean(BookEntity.class));
        return criteria.list();
    }
}
