package library.dao.interfaces;

import library.entities.GenreEntity;

import java.util.List;

public interface GenreDAO {
    List<GenreEntity> getGenres();
}
