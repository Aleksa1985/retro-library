package org.lexsoft.library.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.lexsoft.library.core.model.AuthorEntity;
import org.lexsoft.library.core.repository.AuthorRepository;
import org.lexsoft.library.core.service.AuthorService;

@AllArgsConstructor
public class AuthorsServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public List<AuthorEntity> saveNewAuthors(List<AuthorEntity> authorsList, List<AuthorEntity> existingAuthors) {
    List<AuthorEntity> allAuthors = Optional.ofNullable(existingAuthors).orElseGet(() -> authorRepository.getAllAuthors());
    Pair<List<AuthorEntity>, List<AuthorEntity>> menagedAuthorsPair = manageBookAuthors(allAuthors, authorsList);
    List<AuthorEntity> newAuthors = menagedAuthorsPair.getLeft();
    List<AuthorEntity> existingRelatedBookAuthors = menagedAuthorsPair.getRight();
    newAuthors.forEach(a -> {
      AuthorEntity authorEntity = authorRepository.insertAuthors(a);
      existingRelatedBookAuthors.add(authorEntity);
    });

    return existingRelatedBookAuthors;
  }

  @Override
  public List<AuthorEntity> findAllAuthors() {
    return authorRepository.getAllAuthors();
  }

  private Pair<List<AuthorEntity>,List<AuthorEntity>> manageBookAuthors(List<AuthorEntity> existingAuthors, List<AuthorEntity> newAuthors){
    List<AuthorEntity> newAuthorsToBeSaved = new ArrayList<>();
    List<AuthorEntity> existingBookRelatedAuthors = new ArrayList<>();
    Map<String, List<AuthorEntity>> collect = existingAuthors.stream()
                                                             .collect(Collectors.groupingBy(AuthorEntity::getFullName));
    newAuthors.forEach(na -> {
      if(collect.isEmpty()){
        newAuthorsToBeSaved.add(na);
      }else{
        List<AuthorEntity> authorEntities = collect.get(na.getFullName());
        if(Objects.isNull(authorEntities)){
          newAuthorsToBeSaved.add(na);
        } else {
          existingBookRelatedAuthors.add(authorEntities.get(0));
        }
      }
    });
    return new ImmutablePair<>(newAuthorsToBeSaved, existingBookRelatedAuthors);
  }
}
