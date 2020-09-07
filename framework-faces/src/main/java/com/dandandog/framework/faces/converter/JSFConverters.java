package com.dandandog.framework.faces.converter;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.datepicker.DatePicker;
import org.primefaces.util.MessageFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * @author JohnnyLiu
 * @create 2019-08-05 17:28
 */
@Configuration
public class JSFConverters {
	@Component("localDateTimeConverter")
	@FacesConverter(forClass = LocalDate.class)
	public class LocalDateTimeConverter implements Converter {
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component, context));
			try {
				return LocalDate.parse(value, formatter);
			} catch (Exception e) {
				return null;
			}
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value == null || (value instanceof String && StringUtils.isBlank((String) value))) {
				return "";
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component, context));
			return formatter.format((LocalDate) value);
		}

		private String extractPattern(UIComponent component, FacesContext context) {
			// try to get infos from calendar component
			if (component instanceof Calendar) {
				Calendar calendarComponent = (Calendar) component;
				return calendarComponent.getPattern();
			}
			if (component instanceof DatePicker) {
				DatePicker calendarComponent = (DatePicker) component;
				return calendarComponent.getPattern();
			}

			return null;
		}
	}
}
