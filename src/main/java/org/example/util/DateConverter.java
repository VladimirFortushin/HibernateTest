package org.example.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDate, String> {


    @Override
    public String convertToDatabaseColumn(LocalDate localDate) {
        return  localDate.format(DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    @Override
    public LocalDate convertToEntityAttribute(String s) {
        String[] dbDate = s.split("\\.");
        return LocalDate.of(Integer.parseInt(dbDate[2]), Integer.parseInt(dbDate[1]), Integer.parseInt(dbDate[0]));
    }
}
