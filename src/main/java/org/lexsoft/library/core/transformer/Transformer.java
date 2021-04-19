package org.lexsoft.library.core.transformer;

import java.util.List;

public interface Transformer <K,T>{

  public T transform(K k);
  public List<T> transformBatch(List<K> list);
  public K transformReverse(T t);
  public List<K> transformReverseBatch(List<T> list);


}
