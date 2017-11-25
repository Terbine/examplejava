/**
 * @preserve Copyright (c) 2017 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p/>
 * This material also contains proprietary and confidential information of TERBINE
 * and its suppliers, and may not be used by or disclosed to any person, in
 * whole or in part, without the prior written consent of TERBINE.
 */
package com.terbine.api.example.http;

import com.terbine.cabinet.app.AuthenticatedUser;
import com.terbine.cabinet.dataset.Attachment;
import com.terbine.cabinet.model.ingest.FileUploadInfo;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author brianeno
 */
@Slf4j
public class DatasetServiceClientTest extends CoreHttpServiceClientTest {

    final static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd HH:mm:ss");

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testUploadDataset() throws Exception {
        DatasetService httpService = getDatasetService();

        AuthenticatedUser au = httpService.login(getProperty(USER_PROP_NAME), getProperty(PASSWORD_PROP_NAME));

        assertNotNull(au);

        String fileName = "weatherunderground_20171123_SF.json";
        String fileString = loadSampleJson(fileName);

        FileUploadInfo fui = new FileUploadInfo();
        fui.setDatasetId("1866877e-26cf-4a6f-99e2-dde43a0fc04a");
        fui.setFileName(fileName);
        fui.setDataStart(formatter.parseDateTime("20171123 00:00:01"));
        fui.setDataEnd(formatter.parseDateTime("20171123 23:59:59"));
        Attachment attachment = httpService.uploadDataToMetadata(au.getToken(), fui,
                fileString.getBytes());
        assertNotNull(attachment);
        assertEquals(fileName, attachment.getOriginalName());
    }

    @Test
    public void testGetAllDatasetsForMetadata() throws Exception {

        DatasetService httpService = getDatasetService();

        AuthenticatedUser au = httpService.login(getProperty(USER_PROP_NAME), getProperty(PASSWORD_PROP_NAME));

        assertNotNull(au);

        UUID datasetId = httpService.getDatasetIdForMetadata(au.getToken(), UUID.fromString(getProperty(METADATA_ID_PROP)));
        assertNotNull(datasetId);
        List<Attachment> list = httpService.getAllDatasetsForMetadata(au.getToken(), datasetId, 1, 10);
        assertNotNull(list);
    }

    @Test
    public void testDownloadDataaset() throws Exception {

        DatasetService httpService = getDatasetService();

        AuthenticatedUser au = httpService.login(getProperty(USER_PROP_NAME), getProperty(PASSWORD_PROP_NAME));

        assertNotNull(au);

        UUID datasetId = httpService.getDatasetIdForMetadata(au.getToken(), UUID.fromString(getProperty(METADATA_ID_PROP)));
        assertNotNull(datasetId);
        List<Attachment> list = httpService.getAllDatasetsForMetadata(au.getToken(), datasetId, 1, 10);
        assertNotNull(list);
        assertTrue(list.size() > 0);
        Attachment attachment = list.get(0);

        String outfileName = System.getProperty("java.io.tmpdir") + attachment.getOriginalName();
        httpService.downloadDatasetById(au.getToken(),
                attachment.getId(),
                outfileName);
        File f = new File(outfileName);
        assertTrue(f.exists());
    }


    private DatasetService getDatasetService() {
        String baseUri = getProperty(BASEURL_PROP_NAME);
        DatasetService httpService = new DatasetServiceClient(getHttpClient(), baseUri);
        return httpService;
    }
}