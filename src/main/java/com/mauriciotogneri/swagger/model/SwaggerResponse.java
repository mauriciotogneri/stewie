package com.mauriciotogneri.swagger.model;

import com.mauriciotogneri.swagger.specs.Schema;
import com.mauriciotogneri.swagger.utils.Annotations;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public final class SwaggerResponse
{
    private final transient Integer code;
    private final Schema schema;
    private final Map<String, SwaggerHeaderResponse> headers;
    private final String description;

    public SwaggerResponse(Integer code, Class<?>[] clazz, Class<?> headers, String description)
    {
        this.code = code;
        this.schema = Schema.fromClass(clazz[0]); // TODO
        this.headers = headers(headers);
        this.description = description;
    }

    public String code()
    {
        return String.valueOf(code);
    }

    private Map<String, SwaggerHeaderResponse> headers(Class<?> headers)
    {
        Map<String, SwaggerHeaderResponse> result = new HashMap<>();

        for (Field field : headers.getFields())
        {
            Annotations annotations = new Annotations(field);
            String name = annotations.name();
            Schema schema = Schema.fromClass(field.getType());
            String description = annotations.description();

            result.put(name, new SwaggerHeaderResponse(schema.type(), schema.format(), description));
        }

        return result;
    }
}