/**
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p/>
 * This material also contains proprietary and confidential information of TERBINE
 * and its suppliers, and may not be used by or disclosed to any person, in
 * whole or in part, without the prior written consent of TERBINE.
 */
package com.terbine.api.example.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author brianeno
 */
@Slf4j
public class ContinuousServiceClientTest extends CoreHttpServiceClientTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void uploadDataToMetadata() {
        log.info("Test begin");
    }
}