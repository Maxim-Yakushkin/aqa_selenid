package com.yakushkin.onliner.api.service;

import com.yakushkin.onliner.api.enumiration.ProductType;
import com.yakushkin.onliner.api.model.Product;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeClass
    void setup() {
        productService = new ProductService();
    }

    @DataProvider(name = "productTypeList")
    public static Object[][] productTypeList() {
        return Arrays.stream(ProductType.values())
                .map(value -> new Object[]{value})
                .toArray(Object[][]::new);
    }

    @Test
    void checkAllProductsHaveNameAndFullName() {
        final List<Product> products = productService.getAll()
                .getProducts();

        assertThat(products).noneMatch(product -> product.getName().isBlank() &&
                                                  product.getFullName().isBlank());
    }

    @Test(dataProvider = "productTypeList")
    void checkAllProductsHaveNamePrefixCorrespondingTheirProductType(ProductType productType) {
        final List<Product> products = productService.getProductListBySushiType(productType)
                .getProducts();

        assertThat(products).allMatch(product -> product.getNamePrefix().equals(productType.getNamePrefix()));
    }
}