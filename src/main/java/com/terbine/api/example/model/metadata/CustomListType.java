/**
 * @preserve Copyright (c) 2017 TERBINE. as an unpublished
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
public enum CustomListType {
    METADATA(1) {
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    };

    private static final Map<Integer, CustomListType> LOOKUP = new HashMap();

    static {
        Iterator var0 = EnumSet.allOf(CustomListType.class).iterator();

        while (var0.hasNext()) {
            CustomListType s = (CustomListType) var0.next();
            LOOKUP.put(Integer.valueOf(s.getValue()), s);
        }
    }

    private int value;

    CustomListType(int value) {
        this.value = value;
    }

    public static CustomListType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return (CustomListType) LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid search Type of " + value + " provided");
        }
    }

    public int getValue() {
        return this.value;
    }
}
