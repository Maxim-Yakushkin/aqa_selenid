package com.yakushkin.onliner.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Integer id;
    private String key;
    private String name;
    @JsonAlias("full_name")
    private String fullName;
    @JsonAlias("name_prefix")
    private String namePrefix;
}
