package com.miicrown.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateToYYYYMMDDSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date obj, JsonGenerator jg,
			SerializerProvider sp) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String formattedDate = formatter.format(obj);  
		jg.writeString(formattedDate);  
		
	}

}
