package com.terbine.api.example.model.search;

import com.terbine.api.example.model.metadata.Metadata;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author brianeno
 */

/**
 * This is the class that represents an ElasticSearch document for
 * an indexed metadata
 * <p>
 * Note this is shared as document between indexing, searching and API services
 * and they need to be in sync.
 * <p>
 * If there are major changes, all metadata instances need to be re-indexed
 */
@Getter
@Setter
public class IndexedMetadataItem implements Searchable {

    private Metadata metadata;
    private Integer type;
    private String source;
    private String id;
    private String orgId;
    private String ownerOrgId;
    private String alphaSort;
    private String title;
    private String description;
    private Date dateIndexed;
    private Date dateAdded;
    private Date createDate;
    private Integer metadataType;
    private Integer loadFrequency;
    private Integer loadFrequencyType;
    private Integer updateInterval;
    private Integer updateIntervalType;
    //REMOVE
    private List<String> delivery;
    //REMOVE
    private List<String> relation;
    //REMOVE
    private List<String> categories;
    //REMOVE
    private List<String> ownerType;
    //REMOVE
    private List<String> container;

    private List<String> dataset;
    private List<String> sensor;
    private List<String> format;
    private List<String> gicsCode;
    private List<String> isicCode;
    private List<String> schema;
    private List<String> legal;
    private List<String> regulatory;

    private List<String> grade;
    private List<String> datagrade;
    private List<String> address;
    private String locationOther;
    private List<String> location;
    private List<String> locationSub;

    // V2 Updates
    private List<String> locationName; //done
    private List<String> locationSubName; //done

    // this is the moving text
    private List<String> locationCatName; //done
    private List<String> locationCat; //done

    // this is the text related to moving subtypes free text (e.g. Algo Generated)
    private List<String> locationSensorText; //done

    private List<String> score;  //done
    private List<String> unitOfMeasure; //done
    private List<String> unitOfMeasureName; //done
    private String sourceName; //done
    private String sourceNameText; //done
    private String sourceType; //done
    private String sourceTypeText; //done
    private String manufacturer; //done
    private String manufacturerText; //done
    private String make; //done
    private String model; //done
    private String version; //done
    private List<String> originators; //done
    private List<String> owners; //done
    private List<String> containerName; //done
    private List<String> containerNameText; //done
    private List<String> environment; //done
    private List<String> environmentText; //done
    private List<String> interactor; //done
    private List<String> interactorText; //done

    private List<Coordinate> coordinates;
    // End V2 Updates
    private Integer hasPrice = 0;

    // Start V2 Fixes
    // this is a field for the name of sensor, field "sensor" had the id,
    // so it needs to be an array
    private List<String> sensorName;
    // End V2 Fixes

    private String country; //done

    private Coordinate coordinate;
    private List<String> customListEntryId;
    private List<String> customListEntryName;
}
