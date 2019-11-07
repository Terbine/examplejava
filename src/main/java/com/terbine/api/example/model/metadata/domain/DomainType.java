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
public enum DomainType {
    CATEGORY(1),
    DELIVERY(2),
    DATASET(3),
    SENSOR(4),
    FORMAT(5),
    OWNER(6),
    CONTAINER(7),
    SCHEMA(8),
    EVENT(9),
    LEGAL(10),
    RELATION(11),
    GRADING(12),
    LOCATION(13),
    REGULATORY(14),
    SENSORDATATYPE(15),
    ENVIRONMENT(16),
    LOCATIONCATEGORY(17),
    INTERACTOR(18),
    UNITOFMEASURE(19),
    EXTERNALTYPE(20),
    CONTAINERNAME(21),
    SOURCENAME(22),
    SOURCETYPE(23),
    RADIUSUNIT(24),
    MANUFACTURER(25),
    LOCATION_FIXED(101),
    LOCATION_MOVING(102),
    POLICY_REGULATORY(103),
    POLICY_CUSTOM(104),
    BOOLEAN_VALUES(105);

    private static final Map<Integer, DomainType> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (DomainType s : EnumSet.allOf(DomainType.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    DomainType(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static DomainType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid DomainType of " + value + " provided");
        }
    }
}
