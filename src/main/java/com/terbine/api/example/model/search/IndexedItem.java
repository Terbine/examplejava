/**
 * @preserve Copyright (c) 2018 TERBINE. as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE. and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.model.search;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * @author brianeno
 */

/**
 * This is the class that represents a document for an indexed metadata
 */
@Getter
@Setter
public class IndexedItem implements Searchable {

    private UUID id;
    private UUID itemid;
    private Integer type;
    private DateTime indexedDate;
    private DateTime createdDate;
}
