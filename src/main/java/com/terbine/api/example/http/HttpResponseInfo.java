/**
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p/>
 * This material also contains proprietary and confidential information
 * of TERBINE and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.http;

import lombok.Getter;

/**
 * @author brianeno.
 */
public class HttpResponseInfo {

    @Getter
    private String responseString;
    @Getter
    private Integer responseCode;

    public HttpResponseInfo(String responseString, Integer responseCode) {
        this.responseString = responseString;
        this.responseCode = responseCode;
    }
}
