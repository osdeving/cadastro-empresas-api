package br.com.broadfactor.cadempresas.dto.utils;

import br.com.broadfactor.cadempresas.model.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public static void nonNullAndNestedAwareCopy(Object source, Object target) {
        BeanUtils.copyProperties(source, target, MapperUtils.getNullPropertyNames(source, target));
    }

    // TODO: melhorar solução para funcionalidade PATCH em POJO a partir de outro POJO, atualmente está muito low-level e confuso
    //
    // BeanUtils/ModelMapper/MapStruct e outras libs não suportam o que vou chamar de: 'nested non null deep copy'
    // o mais próximo disso é o BeanUtils do spring framework, mas precisa do método a seguir.
    // Obtém uma lista de propriedades null tratando manualmente campos aninhados usando recursão.
    public static String[] getNullPropertyNames (Object source, Object target) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper targetBean = new BeanWrapperImpl(target);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            Class<?> accessor = src.getPropertyType(pd.getName());
            System.out.println(accessor);
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }else  {
                Class<?> acc = src.getPropertyType(pd.getName());
                String cname = acc.getCanonicalName();
                if(cname.contains("br.com.broadfactor.cadempresas.model")) {
                    Object targetVal = targetBean.getPropertyValue(pd.getName());
                    if(targetVal  != null) {
                        BeanUtils.copyProperties(srcValue,targetVal , getNullPropertyNames(srcValue,targetVal));
                        emptyNames.add(pd.getName());
                    }
                }
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
