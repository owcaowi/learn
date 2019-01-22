package com.example.aprzybylo.learn;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by aprzybylo on 27/03/2018.
 */

public class ExampleAdapter implements JsonDeserializer<Example> {

    @Override
    public Example deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();

        /* Default */
        String name = new String("Jan Kowalski");
        int age = 66;
        String city = new String("Radom");

        if (!JsonNull.class.isInstance(obj.get("name")))
            name = obj.get("name").getAsString();

        if (obj.get("age") != null)
            age = obj.get("age").getAsInt();

        if (obj.get("city") != null)
            city = obj.get("city").getAsString();

        return new Example(name, age, city);
    }
}
