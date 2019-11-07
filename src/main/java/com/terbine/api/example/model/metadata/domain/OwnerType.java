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
public enum OwnerType {
    CORPORATION(60) {
        @Override
        public String toString() {
            return "Corporation";
        }
    },
    INDIVIDUAL(61) {
        @Override
        public String toString() {
            return "Individual";
        }
    },
    GOVERNMENT(62) {
        @Override
        public String toString() {
            return "Government";
        }
    },
    NONPROFIT(63) {
        @Override
        public String toString() {
            return "Nonprofit";
        }
    },
    EDUCATIONAL(64) {
        @Override
        public String toString() {
            return "Educational";
        }
    }, UNKNOWN(65) {
        @Override
        public String toString() {
            return "Uknown";
        }
    }, NGO(66) {
        @Override
        public String toString() {
            return "NGO";
        }
    };

    // Lookup table
    private static final Map<Integer, OwnerType> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (OwnerType s : EnumSet.allOf(OwnerType.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    OwnerType(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static OwnerType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid OwnerType of " + value + " provided");
        }
    }
}
