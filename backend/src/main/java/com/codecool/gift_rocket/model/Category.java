package com.codecool.gift_rocket.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    ALCOHOL("alcohol"),
    SWEET("sweet"),
    KID("kid");

    private String categoryType;

    Category(String categoryType) {
        this.categoryType = categoryType;
    }

    @JsonValue
    public String getCategoryType() {
        return categoryType;
    }


}
