/**
 * @preserve Copyright (c) 2021 TERBINE Inc. as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of Terbine Inc.
 * <p>
 * This material also contains proprietary and confidential information
 * of Terbine Inc. and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of Terbine Inc.
 */
package com.terbine.api.example.model.ingest;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author brianeno
 */
public enum InputType {
    FILE(1),
    STREAM(2),
    PREVIEW(3);

    private static final Map<Integer, InputType> LOOKUP = new HashMap();

    static {
        Iterator var = EnumSet.allOf(InputType.class).iterator();

        while (var.hasNext()) {
            InputType s = (InputType) var.next();
            LOOKUP.put(Integer.valueOf(s.getValue()), s);
        }
    }

    private int value;

    InputType(int value) {
        this.value = value;
    }

    public static InputType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid input Type of " + value + " provided");
        }
    }

    public int getValue() {
        return this.value;
    }
}
