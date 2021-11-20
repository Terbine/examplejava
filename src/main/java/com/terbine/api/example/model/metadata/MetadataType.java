/**
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE. and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.model.metadata;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Corresponds to a type for metadata configuration enum
 *
 * @author brianeno
 */
public enum MetadataType {
    FIXED(1) {
        @Override
        public String toString() {
            return "Fixed";
        }
    },
    CONTINUOUS(2) {
        @Override
        public String toString() {
            return "Continuous";
        }
    },
    EXTERNAL(3) {
        @Override
        public String toString() {
            return "External";
        }
    };


    private static final Map<Integer, MetadataType> LOOKUP = new HashMap();

    static {
        Iterator var0 = EnumSet.allOf(MetadataType.class).iterator();

        while (var0.hasNext()) {
            MetadataType s = (MetadataType) var0.next();
            LOOKUP.put(Integer.valueOf(s.getValue()), s);
        }
    }

    private int value;

    MetadataType(int value) {
        this.value = value;
    }

    public static MetadataType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return (MetadataType) LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid metadata status type of " + value + " provided");
        }
    }

    public int getValue() {
        return this.value;
    }
}
