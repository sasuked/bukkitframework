package io.github.sasuked.bolierplate;

import java.util.List;

public interface ObjectStorage<K, V> {
  
  void setupStorage();
  
  V selectFirst(K key);
  
  void update(V value);
  
  void delete(V value);
  
  List<V> selectAll();
}
