package com.hiklas.cucumber.quickstart.utils;

public enum BrowserType {

    FIREFOX,
    CHROME,
    SAFARI,
    HTMLUNIT;

    public static BrowserType browserForName(String browser) throws IllegalArgumentException {
        for (BrowserType b : values()) {
            if (b.toString().equalsIgnoreCase(browser)) {
                return b;
            }
        }
        throw browserNotFound(browser);
    }

    private static IllegalArgumentException browserNotFound(String outcome) {
        return new IllegalArgumentException(("Invalid browser [" + outcome + "]"));
    }
}
