package com.example.turistguideapidel2.repository;

import com.example.turistguideapidel2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristAttractionRepository {
    List<TouristAttraction> attractions = new ArrayList<>(List.of(
            new TouristAttraction("Havfrue", "Den lille Havfrue. En sej lille dame, på en sej lille sten", "København", List.of("Kunst", "Gratis", "Børnevenlig")),
            new TouristAttraction("Strøget", "Shopping gågade", "København", List.of("Shopping", "Gratis", "Børnevenlig", "Voksne")),
            new TouristAttraction("Runde", "Runde tårn. Ret højt tårn, som er ret rundt", "København", List.of("Kunst", "Børnevenlig", "Voksne", "Byliv")),
            new TouristAttraction("Amalienborg","Nydeligt hus til en nydelig familie", "København", List.of("Kunst", "Gratis", "Voksne"))));

    public List<TouristAttraction> getListOfAttractions(){
        return attractions;
    }
    public List<String> getCities() {
        return new ArrayList<>(List.of("København","Randers","Næstved", "Hareskovsby"));
    }

    public List<String> getTags() {
        return new ArrayList<>(List.of("Børnevenlig","Kunst","Gratis","Naur", "Voksne" ));
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
                if (attractions.get(i).getName().equalsIgnoreCase(touristAttraction.getName())) {
                    attractions.set(i, touristAttraction);
                    return touristAttraction;
                }
            i++;
        }
        return null;
    }
//
//    public void editAttractions(List<TouristAttraction> attractions, String newName, String newDescription, String newBy, String newTags) {
//        attractions.stream()
//                .forEach(attraction -> {
//                    attraction.setName(newName);
//                    attraction.setDescription(newDescription);
//                    attraction.setBy(newBy);
//                    attraction.setTags(attraction.getTags());
//                });
//    }


}
