package com.example.melg.trackit.model;

import java.util.List;

public class Tracking {
    private List<Checkpoints> checkpoints;
    private String language;
    private String unique_token;
    private int tracked_count;
    private String title;
    private String tag;
    private String source;
    private List<String> smses;
    private String signed_by;
    private String shipment_weight_unit;
    private String shipment_weight;
    private String shipment_type;
    private int shipment_package_count;
    private String origin_country_iso3;
    private List<String> emails;
    private String destination_country_iso3;
    private int delivery_time;
    private Custom_fields custom_fields;
    private boolean active;
    private String slug;
    private String tracking_number;
    private String updated_at;
    private String created_at;
    private String id;

    public List<Checkpoints> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Checkpoints> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnique_token() {
        return unique_token;
    }

    public void setUnique_token(String unique_token) {
        this.unique_token = unique_token;
    }

    public int getTracked_count() {
        return tracked_count;
    }

    public void setTracked_count(int tracked_count) {
        this.tracked_count = tracked_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getSmses() {
        return smses;
    }

    public void setSmses(List<String> smses) {
        this.smses = smses;
    }

    public String getSigned_by() {
        return signed_by;
    }

    public void setSigned_by(String signed_by) {
        this.signed_by = signed_by;
    }

    public String getShipment_weight_unit() {
        return shipment_weight_unit;
    }

    public void setShipment_weight_unit(String shipment_weight_unit) {
        this.shipment_weight_unit = shipment_weight_unit;
    }

    public String getShipment_weight() {
        return shipment_weight;
    }

    public void setShipment_weight(String shipment_weight) {
        this.shipment_weight = shipment_weight;
    }

    public String getShipment_type() {
        return shipment_type;
    }

    public void setShipment_type(String shipment_type) {
        this.shipment_type = shipment_type;
    }

    public int getShipment_package_count() {
        return shipment_package_count;
    }

    public void setShipment_package_count(int shipment_package_count) {
        this.shipment_package_count = shipment_package_count;
    }

    public String getOrigin_country_iso3() {
        return origin_country_iso3;
    }

    public void setOrigin_country_iso3(String origin_country_iso3) {
        this.origin_country_iso3 = origin_country_iso3;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getDestination_country_iso3() {
        return destination_country_iso3;
    }

    public void setDestination_country_iso3(String destination_country_iso3) {
        this.destination_country_iso3 = destination_country_iso3;
    }

    public int getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(int delivery_time) {
        this.delivery_time = delivery_time;
    }

    public Custom_fields getCustom_fields() {
        return custom_fields;
    }

    public void setCustom_fields(Custom_fields custom_fields) {
        this.custom_fields = custom_fields;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
