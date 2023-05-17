package com.yakushkin.onliner.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yakushkin.onliner.api.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

    private List<Product> products;
}
