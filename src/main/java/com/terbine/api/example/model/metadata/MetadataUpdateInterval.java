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
 * Corresponds to a type enum for interval for metadata update
 *
 * @author brianeno
 */
public enum MetadataUpdateInterval {
    MILLISECOND(1) {
        @Override
        public String toString() {
            return "Millisecond";
        }
    },
    SECOND(2) {
        @Override
        public String toString() {
            return "Second";
        }
    },
    MINUTE(3) {
        @Override
        public String toString() {
            return "Minute";
        }
    },
    HOUR(4) {
        @Override
        public String toString() {
            return "Hour";
        }
    },
    DAY(5) {
        @Override
        public String toString() {
            return "Day";
        }
    };


    private static final Map<Integer, MetadataUpdateInterval> LOOKUP = new HashMap();

    static {
        Iterator var0 = EnumSet.allOf(MetadataUpdateInterval.class).iterator();

        while (var0.hasNext()) {
            MetadataUpdateInterval s = (MetadataUpdateInterval) var0.next();
            LOOKUP.put(Integer.valueOf(s.getValue()), s);
        }
    }

    private int value;

    MetadataUpdateInterval(int value) {
        this.value = value;
    }

    public static MetadataUpdateInterval get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return (MetadataUpdateInterval) LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid metadata status type of " + value + " provided");
        }
    }

    public int getValue() {
        return this.value;
    }
}
