/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.reactivesw.message.client.utils.serializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Map;

/**
 * Generic Serializer for sending Java objects as JSON.
 *
 * @param <T> class of the entity, representing messages
 * @author Igor Stepanov
 * @author Artem Bilan
 */
public class JsonSerializer<T> {

  protected final ObjectMapper objectMapper;

  public JsonSerializer() {
    this(new ObjectMapper());
    this.objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
    this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public JsonSerializer(ObjectMapper objectMapper) {
    Assert.notNull(objectMapper, "'objectMapper' must not be null.");
    this.objectMapper = objectMapper;
  }

  public byte[] serialize(T data) {
    try {
      byte[] result = null;
      if (data != null) {
        result = this.objectMapper.writeValueAsBytes(data);
      }
      return result;
    } catch (IOException ex) {
      throw new SerializationException("Can't serialize data [" + data + "]", ex);
    }
  }

}
