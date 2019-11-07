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
package com.terbine.api.example.model.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.terbine.api.example.util.CommonDefinitions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.List;

/**
 * @author brianeno.
 */
@ToString(includeFieldNames = true)
@JsonRootName("authuser")
@Getter
public class AuthenticatedUser {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    protected DateTime lastlogin;

    @JsonProperty
    private String username;

    @JsonProperty
    private String displayname;

    @JsonProperty
    private String token;

    @JsonProperty
    private String userid;

    @JsonProperty
    private String orgid;

    @JsonProperty
    private String orgname;

    @JsonProperty
    private Integer orgType;

    @JsonProperty
    @Setter
    private Boolean supervisor = Boolean.FALSE;

    @JsonProperty
    private Integer tokenlifeseconds;

    @JsonProperty
    private Integer numberNotices;

    @JsonProperty
    private List<String> tabs;

    @JsonProperty
    @Setter
    private List<Role> roles;

    @JsonProperty
    @Setter
    private UiTabDefinition tabConfig;

    public AuthenticatedUser() {
    }

    public AuthenticatedUser(String userName, String token, String displayName,
                             String userUUID, String orgid, Integer tokenLifeSeconds,
                             String orgname, Integer orgType, DateTime lastlogin,
                             Integer numberNotices) {
        this.username = userName;
        this.token = token;
        this.displayname = displayName;
        this.userid = userUUID;
        this.orgid = orgid;
        this.orgType = orgType;
        this.tokenlifeseconds = tokenLifeSeconds;
        this.orgname = orgname;
        this.lastlogin = lastlogin;
        this.numberNotices = numberNotices;
    }

    public void setTabs(List<String> tabs) {
        this.tabs = tabs;
    }
}
