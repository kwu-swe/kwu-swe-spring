package com.kwu.swe.global.util;


import com.kwu.swe.global.interfaces.KeyedEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnumConvertUtil {

    public static <E extends Enum<E> & KeyedEnum> E convert(Class<E> enumClass, String key) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> e.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Key not found in " + enumClass.getSimpleName()));
    }

    public static <E extends Enum<E> & KeyedEnum> List<Map<String, String>> getEnumList(Class<E> enumType) {
        return Arrays.stream(enumType.getEnumConstants())
                .map(e -> Map.of(
                        "name", e.name(),
                        "key", e.getKey()
                ))
                .collect(Collectors.toList());
    }
}
