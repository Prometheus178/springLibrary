package library.dao.impl;

import library.dao.interfaces.GenreDAO;
import library.entities.GenreEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class GenreDAOImpl implements GenreDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<GenreEntity> getGenres() {
        return sessionFactory.getCurrentSession().createCriteria(GenreEntity.class).list();
    }
}
