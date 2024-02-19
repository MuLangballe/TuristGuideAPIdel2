package com.example.turistguideapidel2.repository;

import com.example.turistguideapidel2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristAttractionRepository {
    List<TouristAttraction> attractions = new ArrayList<>(List.of(
            new TouristAttraction("Havfrue", "Den lille Havfrue. En sej lille dame, på en sej lille sten", "København", List.of("Kunst", "Gratis", "Børnevenlig")),
            new TouristAttraction("Runde", "Runde tårn. Ret højt tårn, som er ret rundt", "København", List.of("Kunst, Børnevenlig, Voksne ")),
            new TouristAttraction("Amalienborg","Nydeligt hus til en nydelig familie", "København", List.of("Kunst, Gratis, Voksne"))));

    public List<TouristAttraction> getListOfAttractions(){
        return attractions;
    }
    public List<String> getCities() {
        List<String> cities = new ArrayList<>(List.of("København","Randers","Næstved", "Hareskovsby"));
        return cities;
    }

    public List<String> getTags() {
        List<String> aTags = new ArrayList<>(List.of("Børnevenlig","Kunst","Gratis","Naur", "Voksne" ));
        return aTags;
    }

    public ArrayList<String> getAttractionTagsList(String attractionName) {
        ArrayList<String> attractionTagsList = new ArrayList<>();
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(attractionName)) {
                for (String tags : attraction.getTags()) {
                    attractionTagsList.add(tags);
                }
            }
        }
        return attractionTagsList;
    }


    public TouristAttraction getTouristAttractionByName(String name){
        for (TouristAttraction ta : attractions){
            if (ta.getName().toLowerCase().contains(name.toLowerCase())){
                return ta;
            }
        }
        return null;
    }

    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction){
        attractions.add(touristAttraction);
        return touristAttraction;
    }

    public void deleteTouristAttraction(String name){
        TouristAttraction found = new TouristAttraction();

        for (TouristAttraction ta : attractions){
            if (ta.getName().toLowerCase().contains(name.toLowerCase())){
                found = ta;
                break;
            }
        }
        attractions.remove(found);
    }

    public TouristAttraction updateTouristAttraction(TouristAttraction touristAttraction){
        int i = 0;
        while (i < attractions.size()){
            if (touristAttraction.getName().toLowerCase().contains(touristAttraction.getName())){
                attractions.set(i, touristAttraction);
                break;
            }
            i++;
        }
        return null;
    }

    public void addNewAttraction(String name, String description, String city, List<String> tags){
        TouristAttraction newAttraction = new TouristAttraction(name, description, city, tags);
        attractions.add(newAttraction);
    }





}
