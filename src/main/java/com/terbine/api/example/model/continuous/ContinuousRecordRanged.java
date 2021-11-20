/**
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the expressed written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.model.continuous;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.terbine.api.example.util.CommonDefinitions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

/**
 * @author brianeno
 */
@ToString(includeFieldNames = true)
@JsonRootName("ContinuousRecordRanged")
@Getter
@Setter
public class ContinuousRecordRanged extends ContinuousRecord {

    private Integer numberRaw;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime recentDate;


    public ContinuousRecordRanged() {
    }

    public static ContinuousRecordRanged fromContinuousRecord(ContinuousRecord inRec) {
        ContinuousRecordRanged ranged = new ContinuousRecordRanged();

        ranged.setAccountId(inRec.getAccountId());
        ranged.setDatasetId(inRec.getDatasetId());
        ranged.setType(inRec.getType());
        ranged.setStatus(inRec.getStatus());
        ranged.setFileExt(inRec.getFileExt());
        ranged.setDateCreated(inRec.getDateCreated());
        ranged.setEntryTime(inRec.getEntryTime());
        // don't set content
        // ranged.setContent(inRec.getContent());
        ranged.setMetadataId(inRec.getMetadataId());
        return ranged;

    }
}
