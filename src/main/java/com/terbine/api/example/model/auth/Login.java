/**
 * @preserve Copyright (c) 2015 Terbine Inc. as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of Terbine Inc.
 * <p>
 * This material also contains proprietary and confidential information
 * of Terbine Inc. and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of Terbine Inc.
 */
package com.terbine.api.example.model.auth;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.ToString;

/**
 * @author brianeno.
 */
@ToString(includeFieldNames = true)
@JsonRootName("login")
public class Login {

    @Getter
    private final String username;

    @Getter
    private final String password;

    private Login() {
        // Jackson deserialization
        this("", null);
    }

    public Login(final String username, final String password) {
        super();
        this.username = username;
        this.password = password;
    }


}