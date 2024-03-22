import com.example.turistguideapidel2.model.TouristAttraction;
import com.example.turistguideapidel2.repository.TouristAttractionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*public class TouristRepositoryTest {
   private TouristAttractionRepository repository;

    @BeforeEach
   public void setUp() {
        repository = new TouristAttractionRepository();
    }

    @Test
    void attractionSize() {
        //List<TouristAttraction> attractions = repository.getListOfAttractions();
        //assertEquals(4, attractions.size());
    }

    @Test
    /*void numberOfCities() throws SQLException {
        List<String> expectedCities = List.of("København","Randers", "Næstved","Hareskovsby");
        List<String> actualCities = repository.getCities();
        assertEquals(expectedCities.size(),actualCities.size());
    }

    @Test
    void numberOfTags() {
        List<String> expectedTags = List.of("Børnevenlig ", "Kunst", "Gratis", "Natur", "Voksne");
        List <String> acualTags = repository.getTags();
        assertEquals(expectedTags.size(),acualTags.size());
    }


    void showTagsOnAttraction() {
        String attractionName = "Havfrue";
        List<String> expectedTags = List.of("Kunst","Gratis", "Børnevenlig");
        List<String> attractionTagsList = repository.getAttractionTagsList(attractionName);

        assertEquals(expectedTags.size(),attractionTagsList.size());

    }





}
*/
