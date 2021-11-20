/**
 * @preserve Copyright (c) 2021 TERBINE. as an unpublished
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
public enum AttachmentStatus {
    NOT_PROCESSED(1),
    PROCESSED(2),
    ERROR(3),
    DELETED(4);

    private static final Map<Integer, AttachmentStatus> LOOKUP = new HashMap();

    static {
        Iterator var0 = EnumSet.allOf(AttachmentStatus.class).iterator();

        while (var0.hasNext()) {
            AttachmentStatus s = (AttachmentStatus) var0.next();
            LOOKUP.put(Integer.valueOf(s.getValue()), s);
        }
    }

    private int value;

    AttachmentStatus(int value) {
        this.value = value;
    }

    public static AttachmentStatus get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return (AttachmentStatus) LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid attachment status type of " + value + " provided");
        }
    }

    public int getValue() {
        return this.value;
    }
}
