package com.dshvv.blogserver.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 秦腾
 * @version 1.0.0
 * @date 2021/08/06 11:47
 * @since 1.0.0
 */

public class JsonUtils {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        //序列化时候统一日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //设置null时候不序列化(只针对对象属性)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //反序列化时，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 设置此属性，表示序列化字段是，如果有字段匹配失败，不会抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //单引号处理
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, Boolean.TRUE);
        objectMapper.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        /**
         * 解决
         * com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.util.ArrayList` out of START_OBJECT token
         * 错误
         */
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    private JsonUtils() {}

    public static List<Object> json2List(String jsonStr) {
        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Object.class);
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException ex) {

        }
        return Collections.emptyList();
    }

    public static Map<String, Object> json2Map(String jsonStr) {
        MapLikeType mapLikeType = objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, Object.class);
        try {
            return objectMapper.readValue(jsonStr, mapLikeType);
        } catch (IOException ex) {
        }

        return new HashMap<>();
    }

    public static <T> T json2Object(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException ex) {
        }
        return null;
    }

    public static <T> T json2Object(String json, TypeReference<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException ex) {

        }

        return null;
    }

    public static <T> String object2Json(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonGenerationException ex) {

        } catch (JsonMappingException ex) {

        } catch (IOException ex) {

        }
        return null;
    }

    public static <T> List<T> parseArray(String jsonString, Class<T> type) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, type);
            return objectMapper.readValue(jsonString, javaType);
        } catch (IOException ex) {
            throw new IllegalArgumentException("parse json fail:" + jsonString, ex);
        }
    }
}
