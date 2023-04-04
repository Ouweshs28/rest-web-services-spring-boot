package com.ouwesh.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        return getMappingJacksonValue(new MappingJacksonValue(someBean), "field1", "field2");
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeBean> someBeanList = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value5", "value6", "value7"));
        return getMappingJacksonValue(new MappingJacksonValue(someBeanList), "field2", "field3");
    }


    private static MappingJacksonValue getMappingJacksonValue(MappingJacksonValue someBean, String... fields) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        someBean.setFilters(filters);
        return someBean;
    }
}
