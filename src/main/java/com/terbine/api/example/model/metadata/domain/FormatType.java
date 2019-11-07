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
public enum FormatType {
    NONE(502), // default JSON
    CSV(500),
    TAB(501),
    JSON(502),
    XML(503),
    POSITIONAL(504),
    CHARACTER(505),
    XLS(506),
    DOC(507),
    PDF(508),
    ZIP(509),
    TAR(510),
    TARGZ(511),
    UNKNOWN(512),
    KMZ(513),
    KML(514),
    TXT(515),
    ODS(516),
    NNC(517);

    private static final Map<Integer, FormatType> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (FormatType s : EnumSet.allOf(FormatType.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    FormatType(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static FormatType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid FormatType of " + value + " provided");
        }
    }

}
