package com.example.turistguideapidel2.repository;

import com.example.turistguideapidel2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristAttractionRepository {
    List<TouristAttraction> attractions = new ArrayList<>(List.of(
            new TouristAttraction("Havfrue", "Den lille Havfrue. En sej lille dame, på en sej lille sten", "København", List.of("Kunst", "Gratis", "Børnevenlig")),
            new TouristAttraction("Runde", "Runde tårn. Ret højt tårn, som er ret rundt", "København", List.of("Kunst, Børnevenlig ")),
            new TouristAttraction("Amalienborg","Nydeligt hus til en nydelig familie", "København", List.of("Kunst, Gratis,"))));

    public List<TouristAttraction> getListOfAttractions(){
        return attractions;
    }
    public List<String> getCities() {
        List<String> cities = new ArrayList<>(List.of());
        cities.add("København");
        cities.add("Næstved");
        cities.add("Randers");
        cities.add("Hareskovby");
        return cities;
    }

    public List<String> getTags() {
        List<String> aTags = new ArrayList<>(List.of());
        aTags.add("Børnevenlig");
        aTags.add("Kunst");
        aTags.add("Gratis");
        aTags.add("Natur");
        aTags.add("Voksne");
        return aTags;
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





}
