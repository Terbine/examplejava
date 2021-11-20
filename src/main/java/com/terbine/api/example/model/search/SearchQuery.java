/*******************************************************************************
 * @preserve Copyright (c) 2021 TERBINE Inc. as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of Terbine Inc.
 * <p>
 * This material also contains proprietary and confidential information
 * of Terbine Inc. and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of Terbine Inc.
 ******************************************************************************/
package com.terbine.api.example.model.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.terbine.api.example.util.CommonDefinitions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

/**
 * @author brianeno
 */
@Slf4j
@NoArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = true)
@JsonRootName("searchQuery")
public class SearchQuery {

    @JsonProperty("orgId")
    private String orgId;

    @JsonProperty("text")
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_DATEONLY_STD)
    @JsonProperty("dateFrom")
    private DateTime dateFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_DATEONLY_STD)
    @JsonProperty("dateTo")
    private DateTime dateTo;

    @JsonProperty("geo")
    private GeoSearch geo;

    @JsonProperty("filters")
    private Map<String, List<String>> filters;

    public enum Sort {
        ALPHA,
        DATEADDED
    }

    public enum SortOrder {
        ASC,
        DESC
    }
}
