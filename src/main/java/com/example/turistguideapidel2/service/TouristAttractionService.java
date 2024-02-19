package com.example.turistguideapidel2.service;

import com.example.turistguideapidel2.model.TouristAttraction;
import com.example.turistguideapidel2.repository.TouristAttractionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TouristAttractionService {

    private TouristAttractionRepository repository;

    public TouristAttractionService(TouristAttractionRepository touristRepository){
        this.repository = touristRepository;
    }

    public List<TouristAttraction> getListOfAttractions(){
        return repository.getListOfAttractions();
    }

    public TouristAttraction getTouristAttractionByName(String name){
        return repository.getTouristAttractionByName(name);
    }

    public void addTouristAttraction(TouristAttraction touristAttraction){
        repository.addTouristAttraction(touristAttraction);
    }

    public void deleteTouristAttraction(String name){
        repository.deleteTouristAttraction(name);
    }

    public TouristAttraction updateTouristAttraction(TouristAttraction touristAttraction){
        return repository.updateTouristAttraction(touristAttraction);
    }
}
