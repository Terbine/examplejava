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
 * Corresponds to a type of status for metadata configuration enum
 *
 * @author brianeno
 */
public enum MetadataStatus {
    ACTIVE(1) {
        @Override
        public String toString() {
            return "Active";
        }
    },
    NOT_ACTIVE(2) {
        @Override
        public String toString() {
            return "Not Active";
        }
    },
    DELETE(3) {
        @Override
        public String toString() {
            return "Deleted";
        }
    },
    INREVIEW(4) {
        @Override
        public String toString() {
            return "In Review";
        }
    },
    EDITREQUIRED(5) {
        @Override
        public String toString() {
            return "Edit Required";
        }
    };

    private static final Map<Integer, MetadataStatus> LOOKUP = new HashMap();

    static {
        Iterator var0 = EnumSet.allOf(MetadataStatus.class).iterator();

        while (var0.hasNext()) {
            MetadataStatus s = (MetadataStatus) var0.next();
            LOOKUP.put(Integer.valueOf(s.getValue()), s);
        }
    }

    private int value;

    MetadataStatus(int value) {
        this.value = value;
    }

    public static MetadataStatus get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return (MetadataStatus) LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid metadata status type of " + value + " provided");
        }
    }

    public int getValue() {
        return this.value;
    }
}
