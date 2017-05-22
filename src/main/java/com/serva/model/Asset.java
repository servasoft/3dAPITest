package com.serva.model;

import java.util.Map;

/**
 * Created by alexdong on 17/19/05.
 */
public class Asset {
    private String id;
    private String name;
    private String description;
    private Point position;
    private Point position2d;
    private Point rotation;
    private Point location;
    private String parentId;
    private String dataTypeId;
    private String businessTypeId;
    private float weight;
    private Map<String, Object> extend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition2d() {
        return position2d;
    }

    public void setPosition2d(Point position2d) {
        this.position2d = position2d;
    }

    public Point getRotation() {
        return rotation;
    }

    public void setRotation(Point rotation) {
        this.rotation = rotation;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(String dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(String businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
