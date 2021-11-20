/**
 * @preserve Copyright (c) 2021 TERBINE. as an unpublished
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
 * Corresponds to a status of custom list entry
 *
 * @author brianeno
 */
public enum CustomListStatus {
    ACTIVE(1) {
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    },
    INACTIVE(2) {
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    };

    private static final Map<Integer, CustomListStatus> LOOKUP = new HashMap();

    static {
        Iterator var0 = EnumSet.allOf(CustomListStatus.class).iterator();

        while (var0.hasNext()) {
            CustomListStatus s = (CustomListStatus) var0.next();
            LOOKUP.put(Integer.valueOf(s.getValue()), s);
        }
    }

    private int value;

    CustomListStatus(int value) {
        this.value = value;
    }

    public static CustomListStatus get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return (CustomListStatus) LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid search Type of " + value + " provided");
        }
    }

    public int getValue() {
        return this.value;
    }
}
