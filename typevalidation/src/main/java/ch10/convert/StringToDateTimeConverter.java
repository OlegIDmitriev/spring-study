package ch10.convert;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.PostConstruct;

public class StringToDateTimeConverter implements Converter<String, DateTime> {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dateFormatter;
    private String datePattern = DEFAULT_DATE_PATTERN;

    @PostConstruct
    public void init() {
        dateFormatter = DateTimeFormat.forPattern(datePattern);
    }

    @Override
    public DateTime convert(String s) {
        return dateFormatter.parseDateTime(s);
    }

    public static String getDefaultDatePattern() {
        return DEFAULT_DATE_PATTERN;
    }

    public DateTimeFormatter getDateFormatter() {
        return dateFormatter;
    }

    public void setDateFormatter(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
}
