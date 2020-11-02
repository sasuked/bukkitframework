package io.github.sasuked.bolierplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectManager<K , V> {
  
  private final Map<K , V> objectMap = new ConcurrentHashMap<>();
  
  public V getFromKey(K key){
    return objectMap.get(key);
  }
  
  public void register(K key , V value){
    objectMap.put(key,value);
  }
  
  public void release(K key){
    objectMap.remove(key);
  }
  
  public Map<K, V> asMap() {
    return objectMap;
  }
}
