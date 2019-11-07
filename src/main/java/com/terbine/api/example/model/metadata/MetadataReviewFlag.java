/**
 * @preserve Copyright (c) 2017 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.model.metadata;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author brianeno
 */
@JsonRootName("policyGlobalFlag")
public enum MetadataReviewFlag {

    NOT_REVIEW(0) {
        @Override
        public String toString() {
            return "Not Review";
        }
    },
    REVIEW(1) {
        @Override
        public String toString() {
            return "Review";
        }
    };

    private static final Map<Integer, MetadataReviewFlag> LOOKUP = new HashMap<>();

    static {
        Iterator var0 = EnumSet.allOf(MetadataReviewFlag.class).iterator();

        while (var0.hasNext()) {
            MetadataReviewFlag s = (MetadataReviewFlag) var0.next();
            LOOKUP.put(Integer.valueOf(s.getValue()), s);
        }
    }

    private int value;

    MetadataReviewFlag(int value) {
        this.value = value;
    }

    public static MetadataReviewFlag get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid policy reviewflag of " + value + " provided");
        }
    }

    public int getValue() {
        return this.value;
    }
}
