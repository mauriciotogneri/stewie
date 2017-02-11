package com.mauriciotogneri.stewie.specs.parameters;

import com.mauriciotogneri.stewie.model.SwaggerParameter;
import com.mauriciotogneri.stewie.model.SwaggerSchema;
import com.mauriciotogneri.stewie.specs.Annotations;
import com.mauriciotogneri.stewie.specs.Definitions;
import com.mauriciotogneri.stewie.specs.TypeDefinition;

public class DataParameter
{
    private final Class<?> clazz;

    public DataParameter(Class<?> clazz)
    {
        this.clazz = clazz;
    }

    public Boolean isPresent()
    {
        return !clazz.equals(Object.class);
    }

    public SwaggerParameter swaggerParameter(Definitions definitions)
    {
        TypeDefinition typeDef = new TypeDefinition(clazz);
        Annotations annotations = new Annotations(clazz);

        String name = "data";
        String type = "body";
        SwaggerSchema schema = SwaggerSchema.from(typeDef, annotations, definitions);
        String defaultValue = annotations.defaultValue();
        String description = annotations.description();

        return new SwaggerParameter(
                name,
                type,
                schema,
                defaultValue,
                true,
                description);
    }
}