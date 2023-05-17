package com.yakushkin.onliner.api.service;

import com.yakushkin.onliner.api.enumiration.ProductType;
import com.yakushkin.onliner.api.response.ProductResponse;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static com.yakushkin.onliner.api.endpoint.OnlinerEndpoint.SUSHIVESLA_ENDPOINT;
import static io.restassured.RestAssured.given;

public class ProductService {

    @Step("get all Sushivesla products by general endpoint")
    public ProductResponse getAll() {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .get(SUSHIVESLA_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(ProductResponse.class);
    }

    @Step("get all Sushivesla products with filter 'product type' = {productType}")
    public ProductResponse getProductListBySushiType(ProductType productType) {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .param("sushi_typ[0]", productType.name().toLowerCase())
                .get(SUSHIVESLA_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(ProductResponse.class);
    }
}
