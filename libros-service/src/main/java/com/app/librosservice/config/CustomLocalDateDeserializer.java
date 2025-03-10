package com.app.librosservice.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;

public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String date = p.getText();
        try {
            return LocalDate.parse(date); // Intenta formato completo YYYY-MM-DD
        } catch (DateTimeParseException e) {
            try {
                YearMonth ym = YearMonth.parse(date); // Intenta formato YYYY-MM
                return ym.atDay(1); // Convierte a fecha usando el primer día del mes
            } catch (DateTimeParseException e2) {
                return LocalDate.of(Integer.parseInt(date), 1, 1); // Si solo es año, usa 1 de enero
            }
        }
    }
} 