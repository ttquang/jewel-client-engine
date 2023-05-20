package com.jewel.client.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestSuiteDTO {
    String name;
    String code;
    Map<String,String> properties = new HashMap<>();
    List<String> testCaseNames;
    List<TestCaseDTO> testCases;
}
