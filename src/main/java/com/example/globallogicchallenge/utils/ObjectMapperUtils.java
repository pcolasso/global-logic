package com.example.globallogicchallenge.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

public class ObjectMapperUtils {

    private static ModelMapper MODEL_MAPPER;
    private static ObjectMapper OBJECT_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
        MODEL_MAPPER.getConfiguration().setMatchingStrategy(STRICT);
    }

    private ObjectMapperUtils() {
    }

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return MODEL_MAPPER.map(entity, outClass);
    }

    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    public static <S, D> D map(final S source, D destination) {
        MODEL_MAPPER.map(source, destination);
        return destination;
    }

    public static ObjectMapper getInstance() {
        if (OBJECT_MAPPER == null) {
            OBJECT_MAPPER = new ObjectMapper();
            OBJECT_MAPPER.registerModule(new JavaTimeModule());
        }
        return OBJECT_MAPPER;
    }
}

