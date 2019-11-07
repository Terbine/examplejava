/**
 * @preserve Copyright (c) 2017 TERBINE Inc. as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE. and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.model.metadata.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @uathor brianeno
 */
@Getter
@Setter
public class State {
    private Integer id;
    private Integer countryId;
    private String code;
    private String name;
    private String capital;
    private String yearstate;
    private String landarea;

    public State() {
    }
}
