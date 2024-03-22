package com.example.turistguideapidel2.dto;

public class TagDTO {
    private String tagName;

    public TagDTO(String tagName){
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString(){
        return tagName;
    }
}
