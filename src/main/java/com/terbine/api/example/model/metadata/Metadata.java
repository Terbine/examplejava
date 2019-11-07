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
package com.terbine.api.example.model.metadata;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.terbine.api.example.model.metadata.domain.IsicClass;
import com.terbine.api.example.model.metadata.domain.SourceIndicator;
import com.terbine.api.example.model.search.Searchable;
import com.terbine.api.example.util.CommonDefinitions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author brianeno.
 */
@JsonRootName("metadata")
@ToString(includeFieldNames = true)
@Getter
@Setter
public class Metadata implements Searchable, Serializable {

    private UUID id;

    private UUID orgId;

    private String orgName;

    private Identifier identifier;

    private Meta meta;

    private List<Dataset> dataset;

    private List<Container> containers;

    private List<Delivery> deliveries;

    private Integer type = MetadataType.FIXED.getValue();

    private Integer status = 1;

    private Integer loadFrequency;

    private Integer loadFrequencyType;

    private Integer updateInterval;

    private Integer updateIntervalType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime lastDataUpdate;

    private Integer sourceIndicator = SourceIndicator.CROWDSOURCE.getValue();

    private Integer requireLegalReview = MetadataReviewFlag.REVIEW.getValue();

    private Integer hasPrice = 0;

    private String priceText;

    private List<Owner> owners;

    private List<Category> categories;

    private List<GicsCode> gicsCodes;

    private List<IsicClass> isicCodes;

    private Ownership ownership;

    private Environment environment;

    private List<Category> tags;

    private List<Legal> legal;

    private List<Regulatory> regulatory;

    private List<Relation> relations;

    private List<Event> events;

    private List<Grading> gradings;

    private Citation citation;

    private SourceInfo sourceInfo;

    private Integer numberDatasets;

    private List<CustomList> customLists;

    private UUID cloneId;

    @JsonIgnore
    public void addContainer(Container container) {
        if (this.containers == null) {
            this.containers = new ArrayList<>();
        }
        this.containers.add(container);
    }

    @JsonIgnore
    public void addDataset(Dataset dataset) {
        if (this.dataset == null) {
            this.dataset = new ArrayList<>();
        }
        this.dataset.add(dataset);
    }

    @JsonIgnore
    public void addDelivery(Delivery delivery) {
        if (this.deliveries == null) {
            this.deliveries = new ArrayList<>();
        }
        this.deliveries.add(delivery);
    }

    @JsonIgnore
    public void addOwner(Owner owner) {
        if (this.owners == null) {
            this.owners = new ArrayList<>();
        }
        this.owners.add(owner);
    }

    @JsonIgnore
    public void addLegal(Legal legal) {
        if (this.legal == null) {
            this.legal = new ArrayList<>();
        }
        this.legal.add(legal);
    }

    @JsonIgnore
    public void addGicsCode(GicsCode gicsCode) {
        if (this.gicsCodes == null) {
            this.gicsCodes = new ArrayList<>();
        }
        this.gicsCodes.add(gicsCode);
    }

    @JsonIgnore
    public void addRelation(Relation relation) {
        if (this.relations == null) {
            this.relations = new ArrayList<>();
        }
        this.relations.add(relation);
    }

    @JsonIgnore
    public void addEvent(Event event) {
        if (this.events == null) {
            this.events = new ArrayList<>();
        }
        this.events.add(event);
    }

    @JsonIgnore
    public void addRegulatory(Regulatory regulatory) {
        if (this.regulatory == null) {
            this.regulatory = new ArrayList<>();
        }
        this.regulatory.add(regulatory);
    }

    @JsonIgnore
    public void addGrading(Grading grading) {
        if (this.gradings == null) {
            this.gradings = new ArrayList<>();
        }
        this.gradings.add(grading);
    }

    @JsonIgnore
    public void addCustomList(CustomList customList) {
        if (this.customLists == null) {
            this.customLists = new ArrayList<>();
        }
        this.customLists.add(customList);
    }
}