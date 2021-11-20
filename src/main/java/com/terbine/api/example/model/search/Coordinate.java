/**
 * @preserve Copyright (c) 2021 TERBINE. as an unpublished
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

/**
 * @author brianeno
 */

/**
 * This is the class that represents a document for an indexed coordinate
 * within metadata location
 */
@Getter
@Setter
public class Coordinate implements Searchable {

    private String lon;
    private String lat;
    private String alt;
    // V2 Updates
    private String label;
    private String radius;
    // End Updates
}
