package com.yakushkin.enumiration;

public enum SearchFrameTabs {
    IN_CATALOG("в каталоге"),
    IN_NEWS("в новостях"),
    IN_FLEA_MARKET("на барахолке"),
    IN_FORUM("на форуме");

    private final String name;

    SearchFrameTabs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
