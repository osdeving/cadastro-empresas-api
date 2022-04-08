package br.com.broadfactor.cadempresas.dto.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

    private static ModelMapper modelMapper;

    static {
        if(modelMapper == null) {
            modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        }
    }

    public static <D> D map(Object source, Type destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
