package com.chedaojunan.report.serdes;

import java.util.Map;

import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPOJODeserializer<T> implements Deserializer<T> {
  private ObjectMapper objectMapper = new ObjectMapper();
  private Class<T> tClass;

  public JsonPOJODeserializer() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public void configure(Map<String, ?> props, boolean isKey) {
    tClass = (Class<T>) props.get("JsonPOJOClass");
  }

  @Override
  public T deserialize(String topic, byte[] bytes) {
    if (bytes == null)
      return null;

    T data;
    try {
      data = objectMapper.readValue(bytes, tClass);
    } catch (Exception e) {
      throw new SerializationException(e);
    }

    return data;
  }

  @Override
  public void close() {

  }
}
