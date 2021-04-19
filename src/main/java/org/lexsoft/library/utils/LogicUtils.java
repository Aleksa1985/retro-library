package org.lexsoft.library.utils;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class LogicUtils {

  public static <T,K> K switchTypeWithFunctions(Object o , Function<T,K>... functions){
    K k = null;
    for (Function f : functions){
      Object apply = f.apply(o);
      if (Objects.nonNull(apply)) {
        k = (K) apply;
      }
    }
    return k;
  }

  public static <T,K> Function functionsCase(Class<T> cls, Function<T,K> function){
    return obj ->  Optional.of(obj).filter(cls::isInstance).map(cls::cast).map(c -> function.apply(c)).orElse(null);
  }

  public static <T> void switchType(Object o, Consumer... a) {
    for (Consumer consumer : a) {
      consumer.accept(o);
    }
  }

  public static <T> Consumer caze(Class<T> cls, Consumer<T> c) {
    return obj -> Optional.of(obj).filter(cls::isInstance).map(cls::cast).ifPresent(c);
  }

}
