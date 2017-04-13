package io.reactivesw.message.client.utils.serializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Generic Serializer for sending Java objects as JSON.
 *
 * @author Igor Stepanov
 * @author Artem Bilan
 */
public class JsonSerializer {

  /**
   * object mapper.
   */
  protected transient final ObjectMapper objectMapper;

  /**
   * default constructor.
   */
  public JsonSerializer() {
    this(new ObjectMapper());
    this.objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
    this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  /**
   * constructor with mapper.
   *
   * @param objectMapper object mapper
   */
  public JsonSerializer(ObjectMapper objectMapper) {
    Assert.notNull(objectMapper, "'objectMapper' must not be null.");
    this.objectMapper = objectMapper;
  }

  /**
   * serialize.
   *
   * @param data object
   * @return Json string
   */
  public String serialize(Object data) {
    try {
      String result = null;
      if (data != null) {
        result = this.objectMapper.writeValueAsString(data);
      }
      return result;
    } catch (IOException ex) {
      throw new SerializationException("Can't serialize data [" + data + "]", ex);
    }
  }
}
