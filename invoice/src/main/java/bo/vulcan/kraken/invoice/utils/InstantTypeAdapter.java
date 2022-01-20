package bo.vulcan.kraken.invoice.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.Instant;

import java.lang.reflect.Type;

public class InstantTypeAdapter implements JsonDeserializer<Instant>, JsonSerializer<Instant> {

    @Override
    public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json == null ? null : DateTimeHelper.toInstant(json.getAsString());
    }

    @Override
    public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
        return src == null ? null : new JsonPrimitive(DateTimeHelper.fromInstant(src));
    }
}
