package com.yakushkin.onliner.api.enumiration;

import org.apache.commons.lang3.StringUtils;

public enum ProductType {

    SALADS(StringUtils.EMPTY),
    SET("Сет"),
    SOUCE("Соус"),
    GARNIER(StringUtils.EMPTY),
    ROLL("Роллы"),
    DESERT(StringUtils.EMPTY);

    private final String namePrefix;

    ProductType(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getNamePrefix() {
        return namePrefix;
    }
}
