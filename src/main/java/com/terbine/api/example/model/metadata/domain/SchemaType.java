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
public enum SchemaType {
    AVRO(80),
    JSON(81),
    XSD(82),
    NONE(83),
    UNKNOWN(84),
    TEXT(85);

    private static final Map<Integer, SchemaType> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (SchemaType s : EnumSet.allOf(SchemaType.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    SchemaType(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static SchemaType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid SchemaType of " + value + " provided");
        }
    }

}
