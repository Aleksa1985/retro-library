package org.lexsoft.library.core.transformer.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.lexsoft.library.core.model.AuthorEntity;
import org.lexsoft.library.core.transformer.Transformer;
import org.lexsoft.library.web.model.AuthorDto;

/**
 * NOTE: transform and transformRevers methods are the same because entity and dto objects
 * have the same structure, but in real life scenario these two objects can be totally different
 * fieldNames , number of fields etc, so these two methods will be completely different.
 */
public class AuthorTransformerImpl implements Transformer<AuthorDto, AuthorEntity> {

  @Override
  public AuthorEntity transform(AuthorDto authorDto) {
    return AuthorEntity.builder()
                       .id(authorDto.getId())
                       .firstName(authorDto.getFirstName())
                       .lastName(authorDto.getLastName())
                       .build();
  }

  @Override
  public List<AuthorEntity> transformBatch(List<AuthorDto> list) {
    return list.stream().map(e -> transform(e)).collect(Collectors.toList());
  }

  @Override
  public AuthorDto transformReverse(AuthorEntity authorEntity) {
    return AuthorDto.builder()
                    .id(authorEntity.getId())
                    .firstName(authorEntity.getFirstName())
                    .lastName(authorEntity.getLastName())
                    .build();
  }

  @Override
  public List<AuthorDto> transformReverseBatch(List<AuthorEntity> list) {
    return list.stream().map(e -> transformReverse(e)).collect(Collectors.toList());

  }
}
