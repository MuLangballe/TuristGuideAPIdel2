package com.example.turistguideapidel2.repository;

import com.example.turistguideapidel2.dto.TagDTO;
import com.example.turistguideapidel2.dto.TouristAttractionDTO;
import com.example.turistguideapidel2.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristAttractionRepository {

    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String pwd;

    public List<TouristAttractionDTO> getListOfAttractions() {
        List<TouristAttractionDTO> touristAttractionList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "SELECT TName, TDescription, CityName, Tag FROM attraction\n" +
                    "JOIN City ON attraction.CityNO = city.CityID\n" +
                    "JOIN attractiontags ON attraction.AttractionID = attractiontags.attractionID\n" +
                    "JOIN tags ON tags.TagNO = attractiontags.TagID;";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            String currentAttractionName = "";
            TouristAttractionDTO currentAttractionDTO = null;
            while (rs.next()) {
                String attractionName = rs.getString("attraction.TName");
                String description = rs.getString("attraction.TDescription");
                String city = rs.getString("city.CityName");
                TagDTO tagDTO = new TagDTO(rs.getString("tags.Tag"));
                if (attractionName.equals(currentAttractionName)){
                    currentAttractionDTO.addTag(tagDTO);
                } else {
                    currentAttractionDTO = new TouristAttractionDTO(attractionName, description, city, new ArrayList<>(List.of(tagDTO)));
                    currentAttractionName = attractionName;
                    touristAttractionList.add(currentAttractionDTO);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return touristAttractionList;
    }

        public List<String> getCities() throws SQLException {
        List<String> cities = null;
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)){
            String SQL = "SELECT cityName FROM city";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String city = rs.getString("CityName");
                cities.add(city);
            }
        }
        return cities;
    }

    public List<String> getTags() {
        List<String> tags = null;
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)){
            String SQL = "SELECT Tag FROM Tags";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tag = rs.getString("Tag");
                tags.add(tag);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tags;
    }

    public List<TagDTO> getAttractionTagListDTO(String attractionName){
        List<TagDTO> tagList = new ArrayList<>();
        TagDTO tagDTO;
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)){
            String SQL = "SELECT tag FROM attractiontags\n" +
                    "join attraction on attraction.attractionID = attractiontags.attractionID\n" +
                    "join tags on tags.TagNO = attractiontags.TagID\n" +
                    "where attraction.TName = ?;";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, attractionName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tagName = rs.getString("tags.tag");
                tagDTO = new TagDTO(tagName);
                tagList.add(tagDTO);
            }
            return tagList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TouristAttractionDTO getTouristAttractionByName(String attractionName){
        TouristAttractionDTO foundAttraction = null;
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)){
            String SQL = "SELECT TName, TDescription, CityName, Tag FROM attraction\n" +
                    "JOIN City ON attraction.CityNO = city.CityID\n" +
                    "JOIN attractiontags ON attraction.AttractionID = attractiontags.attractionID\n" +
                    "JOIN tags ON tags.TagNO = attractiontags.TagID" +
                    "WHERE TName = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, attractionName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String aName = rs.getString("attraction.TName");
                String aDescription = rs.getString("attraction.TDescription");
                String aCity = rs.getString("city.CityName");
                List<TagDTO> tagDTOList = getAttractionTagListDTO(attractionName);
                foundAttraction = new TouristAttractionDTO(aName, aDescription, aCity, tagDTOList);
            }
            return foundAttraction;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   public void addTouristAttraction(TouristAttraction touristAttraction) {
       try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
           String SQL = "INSERT INTO attraction(TName, TDescription, CityNO) /n" +
                    "VALUES ( ?, ?, ?);";
           PreparedStatement ps = connection.prepareStatement(SQL);
           ps.setString(1, touristAttraction.getName());
           ps.setString(2, touristAttraction.getDescription());
           ps.setString(3, touristAttraction.getCity());
           ps.executeUpdate();

           for (String tag: touristAttraction.getTags()){

               SQL = "INSERT INTO attractionTags " +
                       "VALUES (?,?);";
               ps = connection.prepareStatement(SQL);
               ps.setString(1, getAttractionNumberFromName(touristAttraction.getName()));
               ps.setString(2, getTagNumberFromName(tag));

               ps.executeUpdate();
           }

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

   }

       public void deleteTouristAttraction (String attractionName){
           try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
               String SQL = "DELETE FROM AttractionTags \n" +
                       "WHERE attractionID = (SELECT AttractionID\n" +
                       "FROM attraction \n" +
                       "WHERE TName = ?)";
               PreparedStatement ps = connection.prepareStatement(SQL);
               ps.setString(1, attractionName);
               ps.executeUpdate();

               String SQL2 = "DELETE FROM Attraction \n" +
                       "WHERE attractionID = (SELECT AttractionID\n" +
                       "FROM attraction \n" +
                       "WHERE TName = ?);";
               PreparedStatement ps2 = connection.prepareStatement(SQL);
               ps.setString(1, attractionName);
               ps.executeUpdate();

           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }


    public void updateTouristAttraction(TouristAttraction touristAttraction) {
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "UPDATE attraction \" +\n" +
                    "                    \"SET a_name = ?, a_description = ?, city_id = ? \" +\n" +
                    "                    \"WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, touristAttraction.getName());
            ps.setString(2, touristAttraction.getDescription());
            ps.setString(3, getLocationNumberFromName(touristAttraction.getCity()));
            ps.setString(4, getAttractionNumberFromName(touristAttraction.getName()));

            ps.executeUpdate();

            for (String tag: touristAttraction.getTags()){
                SQL = "INSERT INTO attractiontags " +
                "VALUES (?,?);";

                ps = connection.prepareStatement(SQL);
                ps.setString(1, getAttractionNumberFromName(touristAttraction.getName()));
                ps.setString(2, getTagNumberFromName(tag));

                ps.executeUpdate();

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        // Hjæpemetoder:
       public String getLocationNumberFromName (String city) {
           int number = 0;

           try (Connection connection = DriverManager.getConnection(db_url, username, pwd))
           {

               String SQL = "SELECT ID FROM location WHERE city = ?;";
               PreparedStatement ps = connection.prepareStatement(SQL);
               ps.setString(1, city);

               ResultSet rs = ps.executeQuery();

               if(rs.next()){
                   number = rs.getInt("ID");
               }
           }
           catch (SQLException e) {
               throw new RuntimeException(e);
           }
           return String.valueOf(number);
       }

    public String getAttractionNumberFromName(String name){
        int number = 0;

        try (Connection connection = DriverManager.getConnection(db_url, username, pwd))
        {

            String SQL = "SELECT ID FROM attraction WHERE a_name = ?;";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1,name);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                number = resultSet.getInt("ID");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(number);
    }

    public String getTagNumberFromName(String name){
        int number = 0;

        try (Connection connection = DriverManager.getConnection(db_url, username, pwd))
        {

            String SQL = "SELECT ID FROM tag WHERE tag = ?;";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1,name);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                number = resultSet.getInt("ID");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(number);
    }

}
