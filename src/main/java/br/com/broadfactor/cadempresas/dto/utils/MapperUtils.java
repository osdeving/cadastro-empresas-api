package br.com.broadfactor.cadempresas.dto.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

    private static ModelMapper modelMapper;

    static {
        if(modelMapper == null) {
            modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setSkipNullEnabled(true)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
        }
    }

    public static ModelMapper getMapper() {
        return modelMapper;
    }

    public static <D> D map(Object source, Type destinationType) {

        if(source == null) return null;

        return modelMapper.map(source, destinationType);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        if(source == null) return null;

        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
