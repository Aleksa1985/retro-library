package org.lexsoft.library.core.service;

import java.util.List;
import org.lexsoft.library.core.model.AuthorEntity;

public interface AuthorService {

    public List<AuthorEntity> saveNewAuthors(List<AuthorEntity> authorsList,List<AuthorEntity> existingAuthors);

    public List<AuthorEntity> findAllAuthors();

}
