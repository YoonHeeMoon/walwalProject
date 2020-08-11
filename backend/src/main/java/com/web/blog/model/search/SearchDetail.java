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
public class SearchDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String d_id;

    private String d_dong;
    private String d_img1;
    private String d_img2;
    private String d_img3;

    public String getD_dong() {
        return this.d_dong;
    }

    public void setD_dong(String d_dong) {
        this.d_dong = d_dong;
    }

    public String getD_img1() {
        return d_img1;
    }

    public void setD_img1(String d_img1) {
        this.d_img1 = d_img1;
    }

    public String getD_img2() {
        return d_img2;
    }

    public void setD_img2(String d_img2) {
        this.d_img2 = d_img2;
    }

    public String getD_img3() {
        return d_img3;
    }

    public void setD_img3(String d_img3) {
        this.d_img3 = d_img3;
    }
    
    
}