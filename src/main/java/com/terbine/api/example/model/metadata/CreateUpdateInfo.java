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
package com.terbine.api.example.model.metadata;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.terbine.api.example.util.CommonDefinitions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author brianeno.
 */
@ToString(includeFieldNames = true)
@JsonRootName("createUpdateInfo")
@Getter
@Setter
public class CreateUpdateInfo implements Serializable {

    public static final String SYSTEM_USER = "SYSTEM_USER";

    private UUID createUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime createDate;

    private UUID updateUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime updateDate;

    public CreateUpdateInfo() {
        this.createDate = DateTime.now(DateTimeZone.UTC);
    }

    public CreateUpdateInfo(UUID createUser, DateTime createDate) {
        this.createUser = createUser;
        this.createDate = createDate;
    }

    public CreateUpdateInfo(UUID createUser, DateTime createDate, UUID updateUser, DateTime updateDate) {
        this.createUser = createUser;
        this.createDate = createDate;
        this.updateUser = updateUser;
        this.updateDate = updateDate;
    }
}
