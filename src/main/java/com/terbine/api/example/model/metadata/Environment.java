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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author brianeno.
 */
@ToString(includeFieldNames = true)
@JsonRootName("environment")
@Getter
@Setter
public class Environment implements Serializable {

    private Integer containerNameId;

    private String containerName;

    private List<EnvironmentInfo> environmentInfos;

    private List<EnvironmentInfo> interactors;

    public void addInteractor(EnvironmentInfo interactor) {
        if (this.interactors == null) {
            this.interactors = new ArrayList<>();
        }
        this.interactors.add(interactor);
    }

    public void addEnvironmentInfo(EnvironmentInfo environmentInfo) {
        if (this.environmentInfos == null) {
            this.environmentInfos = new ArrayList<>();
        }
        this.environmentInfos.add(environmentInfo);
    }
}
