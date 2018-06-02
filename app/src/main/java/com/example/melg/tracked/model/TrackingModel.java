package com.example.melg.tracked.model;

import java.util.List;

/**
 * Created by melg on 6/2/18.
 */

public class TrackingModel {


    private Tracking tracking;

    public Tracking getTracking() {
        return tracking;
    }



    public static class Tracking {
        private String language;
        private Custom_fields custom_fields;
        private String order_id_path;
        private String order_id;
        private List<String> emails;
        private List<String> smses;
        private String title;
        private String tracking_number;
        private String slug;

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Custom_fields getCustom_fields() {
            return custom_fields;
        }



        public String getOrder_id_path() {
            return order_id_path;
        }

        public void setOrder_id_path(String order_id_path) {
            this.order_id_path = order_id_path;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public List<String> getEmails() {
            return emails;
        }

        public void setEmails(List<String> emails) {
            this.emails = emails;
        }

        public List<String> getSmses() {
            return smses;
        }

        public void setSmses(List<String> smses) {
            this.smses = smses;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTracking_number() {
            return tracking_number;
        }

        public void setTracking_number(String tracking_number) {
            this.tracking_number = tracking_number;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }
    }

    public static class Custom_fields {
        private String product_price;
        private String product_name;

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }
    }
}
