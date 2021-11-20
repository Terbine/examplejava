/*******************************************************************************
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 ******************************************************************************/
package com.terbine.api.example.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author brianeno .
 */
@Slf4j
public class UUIDHelper {

    public static UUID getUUID() {

        UUID uuid = UUID.randomUUID();
        return uuid;
    }

    /**
     * I know using exception for normal flow, but this was the best and most efficient way
     * to check if a string is a UUID
     * @param string
     * @return
     */
    public static boolean isUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static UUID fromString(String id) {
        if (id == null) {
            return null;
        } else {
            try {
                return UUID.fromString(id);
            } catch (Exception ex) {
                log.error("Invalid UUID String passed in [" + id + "]");
                throw new RuntimeException("Invalid UUID String passed in [" + id + "]", ex);
            }
        }
    }

    public static String toString(UUID id, String defaultVal) {
        if (id == null) {
            return defaultVal;
        } else {
            return id.toString();
        }
    }

    public static List<String> convertStringList(List<UUID> inList) {

        List<String> list = new ArrayList<>();
        for (UUID inUUID : inList) {

            list.add(inUUID.toString());
        }
        return list;
    }
}
