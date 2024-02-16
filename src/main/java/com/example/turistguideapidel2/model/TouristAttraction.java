package com.example.turistguideapidel2.model;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String by;
    private List<String> tags;


    public TouristAttraction(String name, String description, String by, List<String> tags){
        this.name = name;
        this.description = description;
        this.by = by;
        this.tags = tags;
    }

    public TouristAttraction(){

    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getBy(){
        return by;
    }
    public void setBy(String by) {
        this.by = by;
    }
}
