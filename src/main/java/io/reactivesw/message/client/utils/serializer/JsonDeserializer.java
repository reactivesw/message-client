
package io.reactivesw.message.client.utils.serializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Arrays;

/**
 * Generic Deserializer for receiving JSON from Kafka and return Java objects.
 *
 * @param <T> class of the entity, representing messages
 */
public class JsonDeserializer<T> {

  /**
   * object mapper.
   */
  protected transient ObjectMapper objectMapper;

  /**
   * target type.
   */
  protected transient Class<T> targetType;

  /**
   * object reader.
   */
  private transient ObjectReader reader;

  /**
   * default constructor.
   */
  protected JsonDeserializer() {
    this((Class<T>) null);
  }

  /**
   * constructor with object mapper.
   *
   * @param objectMapper object mapper.
   */
  protected JsonDeserializer(ObjectMapper objectMapper) {
    this(null, objectMapper);
  }

  /**
   * constructor with target type.
   *
   * @param targetType type
   */
  public JsonDeserializer(Class<T> targetType) {
    this(targetType, new ObjectMapper());
    this.objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
    this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  /**
   * constructor with target type and object mapper.
   *
   * @param targetType   type
   * @param objectMapper mapper
   */
  public JsonDeserializer(Class<T> targetType, ObjectMapper objectMapper) {
    Assert.notNull(objectMapper, "'objectMapper' must not be null.");
    Assert.notNull(targetType, "'targetType' cannot be resolved.");

    this.objectMapper = objectMapper;
    this.targetType = targetType;
  }

  /**
   * deserialize data
   *
   * @param data byte[]
   * @return Object
   */
  public T deserialize(byte[] data) {
    if (this.reader == null) {
      this.reader = this.objectMapper.readerFor(this.targetType);
    }
    try {
      T result = null;
      if (data != null) {
        result = this.reader.readValue(data);
      }
      return result;
    } catch (IOException e) {
      throw new SerializationException("Can't deserialize data [" + Arrays.toString(data) + "]", e);
    }
  }

}
