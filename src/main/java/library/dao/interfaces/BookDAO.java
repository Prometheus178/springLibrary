package library.dao.interfaces;

import library.entities.AuthorEntity;
import library.entities.BookEntity;
import library.entities.GenreEntity;

import java.util.List;

public interface BookDAO {

    List<BookEntity> getBooks();
    List<BookEntity> getBooks(AuthorEntity author);
    List<BookEntity> getBooks(String bookName);
    List<BookEntity> getBooks(GenreEntity genre);
    List<BookEntity> getBooks(Character letters);
    Object getFieldValue(Long id, String fieldName);
}
