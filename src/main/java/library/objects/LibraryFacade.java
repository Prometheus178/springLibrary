package library.objects;

import library.dao.interfaces.BookDAO;
import library.entities.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryFacade {
    private BookDAO bookDAO;

    @Autowired

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        books = bookDAO.getBooks();

    }
    private List<BookEntity> books;

    public List<BookEntity> getBooks() {
        return books;
    }

}
