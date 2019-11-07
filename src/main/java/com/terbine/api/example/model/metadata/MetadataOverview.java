/**
 * @preserve Copyright (c) 2018 TERBINE as an unpublished
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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * @author brianeno.
 */
@JsonRootName("metadata")
@ToString(includeFieldNames = true)
@Getter
@Setter
public class MetadataOverview {

    private UUID id;

    private String name;

    private String locationCategory;
}