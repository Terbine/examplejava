/**
 * @preserve Copyright (c) 2015 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.model.metadata.domain;

import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author brianeno.
 */
public enum GradingLevel {
    Unknown(129) {
        @Override
        public String toString() {
            return "Unknown";
        }
    },
    Bronze(120) {
        @Override
        public String toString() {
            return "Bronze";
        }
    },
    Silver(121) {
        @Override
        public String toString() {
            return "Silver";
        }
    },
    Gold(122) {
        @Override
        public String toString() {
            return "Gold";
        }
    },
    Platinum(123) {
        @Override
        public String toString() {
            return "Platinum";
        }
    };

    // Lookup table
    private static final Map<Integer, GradingLevel> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (GradingLevel s : EnumSet.allOf(GradingLevel.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    GradingLevel(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static GradingLevel get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid GradingLevel of " + value + " provided");
        }
    }

}
