package com.nwook.wallet.service.model;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateSerializer extends JsonSerializer<Date> {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(final Date dateTime,
                          final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(FORMAT.format(dateTime));
    }
}
