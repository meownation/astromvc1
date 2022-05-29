package com.astromvc1.model;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

// based on https://tedblob.com/spring-requestparam-enum-lowercase/
@Component
public class StringToAstroSignEnumConverter implements Converter<String, AstroSign> {

    @Override
    public AstroSign convert(String source) {
        try {
            return source.isEmpty() ? null : AstroSign.valueOf(source.trim().toUpperCase(Locale.ROOT));
        } catch(Exception e) {
            return AstroSign.UNKNOWN;
        }
    }
}

