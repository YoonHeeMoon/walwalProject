package com.web.blog.model.search;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Search {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String s_id;

    private String s_name;
    private String s_price;
    private String s_link;
    private String s_img;
    private String s_info;
    
    public String getS_img() {
        return this.s_img;
    }

    public void setS_img(String s_img) {
        this.s_img = s_img;
    }

    public String getS_link() {
        return this.s_link;
    }

    public void setS_link(String s_link) {
        this.s_link = s_link;
    }

    public String getS_name() {
        return this.s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_price() {
        return this.s_price;
    }

    public void setS_price(String s_price) {
        this.s_price = s_price;
    }

    public String getS_info() {
        return this.s_info;
    }

    public void setS_info(String s_info) {
        this.s_info = s_info;
    }
    
}