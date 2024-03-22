package com.example.turistguideapidel2.service;

import com.example.turistguideapidel2.dto.TagDTO;
import com.example.turistguideapidel2.dto.TouristAttractionDTO;
import com.example.turistguideapidel2.model.TouristAttraction;
import com.example.turistguideapidel2.repository.TouristAttractionRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TouristAttractionService {

    private final TouristAttractionRepository repository;

    public TouristAttractionService(TouristAttractionRepository touristRepository){
        this.repository = touristRepository;
    }

    public List<TouristAttractionDTO> getListOfAttractions(){
        return repository.getListOfAttractions();
    }

    public TouristAttractionDTO getTouristAttractionByName(String name){
        return repository.getTouristAttractionByName(name);
    }

   public void addTouristAttraction(TouristAttraction touristAttraction){
        repository.addTouristAttraction(touristAttraction);
    }

    public void deleteTouristAttraction(String name){
        repository.deleteTouristAttraction(name);
    }

    public void updateTouristAttraction(TouristAttraction touristAttraction){
         repository.updateTouristAttraction(touristAttraction);
    }



    public List<TagDTO> getTagsForSelectedAttraction(String attractionName){
        return repository.getAttractionTagListDTO(attractionName);
    }

    public List<String> getTags() {
        return repository.getTags();
    }

    public List<String> getCities() throws SQLException {
        return repository.getCities();
    }


}
