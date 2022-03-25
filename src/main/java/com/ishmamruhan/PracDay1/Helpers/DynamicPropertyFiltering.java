package com.ishmamruhan.PracDay1.Helpers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.ArrayList;
import java.util.List;

public class DynamicPropertyFiltering {
    private final Object data;
    private final String[] propertiesWeWant;

    public DynamicPropertyFiltering(Object data, String... propertiesWeWant) {
        this.data = data;
        this.propertiesWeWant = propertiesWeWant;
    }

    /**
     *
     * @param jsonFilter  : This is Our Custom Json Filter.
     * This Filter name must be annotated with @JsonFilter("jsonFilter") above the class, we are working with
     */

    public MappingJacksonValue applyFilter(String jsonFilter){
        //Properties We want to keep
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept(propertiesWeWant);

        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter(jsonFilter,simpleBeanPropertyFilter);

        //Filtering the properties
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(data);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }
}
