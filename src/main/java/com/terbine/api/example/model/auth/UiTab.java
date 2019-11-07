/*******************************************************************************
 * @preserve Copyright (c) 2017 TERBINE  as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 ******************************************************************************/
package com.terbine.api.example.model.auth;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author brianeno
 */
@Slf4j
@NoArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = true)
@JsonRootName("uiTabs")
public class UiTab {

    private String tab;

    private Integer numNotices;
}
