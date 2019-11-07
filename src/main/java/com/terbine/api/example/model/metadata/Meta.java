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
package com.terbine.api.example.model.metadata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author brianeno.
 */
@ToString(includeFieldNames = true)
@Getter
@Setter
public class Meta implements Serializable {

    public static final String METADATA_VERSION = "1.0";

    protected CreateUpdateInfo createUpdateInfo;

    private String name;

    private String description;

    private String imageUrl;

    private String version = METADATA_VERSION;

    private String startDate;

    private String endDate;

    private String tid;
}
