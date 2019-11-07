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

import com.fasterxml.jackson.annotation.JsonRootName;
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
@JsonRootName("configuration")
public class MetadataConfiguration implements Serializable {

    protected String url;
    protected String comment;
}
