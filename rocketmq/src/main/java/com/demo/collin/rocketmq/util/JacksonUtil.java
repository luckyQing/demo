package com.demo.collin.rocketmq.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * jackson工具类
 *
 * @author collin
 * @date 2020-08-24
 */
@Slf4j
public final class JacksonUtil {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    /**
     * 对象转json
     *
     * @param value
     * @return
     */
    public static final String toJson(Object value) {
        String result = null;
        try {
            result = OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("write to String error", e);
        }
        return result;
    }

    /**
     * 对象转json
     *
     * @param value
     * @return
     */
    public static final byte[] toBytes(Object value) {
        byte[] result = null;
        try {
            result = OBJECT_MAPPER.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            log.error("write to bytes error", e);
        }
        return result;
    }

    /**
     * json转对象
     *
     * @param content
     * @param valueType
     * @return
     */
    public static <T> T parseObject(String content, Class<T> valueType) {
        T t = null;
        try {
            t = OBJECT_MAPPER.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            log.error("parse object error", e);
        }

        return t;
    }

    /**
     * json转对象
     *
     * @param content
     * @param valueTypeRef
     * @return
     */
    public static <T> T parseObject(String content, TypeReference<T> valueTypeRef) {
        T t = null;
        try {
            t = OBJECT_MAPPER.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            log.error("parse object error", e);
        }

        return t;
    }

    public static JsonNode parseObject(String content) {
        JsonNode jsonNode = null;
        try {
            jsonNode = OBJECT_MAPPER.readTree(content);
        } catch (JsonProcessingException e) {
            log.error("parse object error", e);
        }

        return jsonNode;
    }

    public static Map<String, Object> toMap(Object object) {
        try {
            String content = OBJECT_MAPPER.writeValueAsString(object);
            return OBJECT_MAPPER.readValue(content, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            log.error("Write to map error:{}", object, e);
            return null;
        }
    }

}