package library.objects;

import library.dao.interfaces.BookDAO;
import library.entities.AuthorEntity;
import library.entities.BookEntity;
import library.enums.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("libraryFacade")
@Scope("singleton")
public class LibraryFacade {
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private SearchCriteria searchCriteria;

    private List<BookEntity> books;

    public List<BookEntity> getBooks() {
        if (books ==null){
            books = bookDAO.getBooks();
        }
        return books;
    }

    public void searchBookByLetter(){
        books = bookDAO.getBooks(searchCriteria.getLetter());
    }

    public void searchByGenre() {
        books = bookDAO.getBooks(searchCriteria.getGenre());
    }

    public void searchByText() {
        switch (searchCriteria.getSearchType()){
            case TITLE:
                books = bookDAO.getBooks(searchCriteria.getText());
                break;
            case AUTHOR:
                books = bookDAO.getBooks(new AuthorEntity(searchCriteria.getText()));
                break;
        }

    }

}
