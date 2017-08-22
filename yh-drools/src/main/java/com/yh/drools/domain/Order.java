package com.yh.drools.domain;

import com.yh.drools.Base;

public class Order extends Base{
  
    private String name = "";
    private Integer sumprice =0;
    private Integer DiscountPercent=0;;
      
    public Integer getSumprice() {
        return sumprice;  
    }  
    public void setSumprice(Integer sumprice) {
        this.sumprice = sumprice;  
    }  
      
    public String getName() {
        return name;  
    }  
    public void setName(String name) {
        this.name = name;  
    }  
    public Integer getDiscountPercent() {
        return DiscountPercent;  
    }  
    public void setDiscountPercent(Integer DiscountPercent) {
        this.DiscountPercent = DiscountPercent;  
    }


}  