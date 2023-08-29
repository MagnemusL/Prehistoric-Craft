package net.smazeee.prehistoric.util;

import net.minecraft.util.StringRepresentable;

public enum TripleBlockHalf implements StringRepresentable {
    UPPER,
    MIDDLE,
    LOWER;

    public String toString() {
        return this.getSerializedName();
    }

    public String getSerializedName() {
        return switch (this) {
            case UPPER -> "upper";
            case MIDDLE -> "middle";
            case LOWER -> "lower";
        };
    }
}
