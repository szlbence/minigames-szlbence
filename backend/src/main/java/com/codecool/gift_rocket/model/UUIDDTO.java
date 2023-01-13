package com.codecool.gift_rocket.model;

import java.util.UUID;

public class UUIDDTO {
    private UUID lowerLevelId;
    private UUID higherLevelId;

    public UUIDDTO(UUID lowerLevelId, UUID higherLevelId) {
        this.lowerLevelId = lowerLevelId;
        this.higherLevelId = higherLevelId;
    }

    public UUID getLowerLevelId() {
        return lowerLevelId;
    }

    public UUID getHigherLevelId() {
        return higherLevelId;
    }
}
