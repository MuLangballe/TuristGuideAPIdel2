package com.example.turistguideapidel2.controller;

import com.example.turistguideapidel2.dto.TagDTO;
import com.example.turistguideapidel2.dto.TouristAttractionDTO;
import com.example.turistguideapidel2.model.TouristAttraction;
import com.example.turistguideapidel2.service.TouristAttractionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("ALL")
@Controller
@RequestMapping("/attractions")
public class TouristAttractionController {
    TouristAttractionService touristAttractionService;

    public TouristAttractionController(TouristAttractionService touristAttractionService){
        this.touristAttractionService = touristAttractionService;
    }

    @GetMapping("")
    public String showAttractions(Model model){
        List<TouristAttractionDTO> attractionList = touristAttractionService.getListOfAttractions();
        model.addAttribute("attractions", attractionList);
        return "attractionList";
    }

    @GetMapping("/{name}/tags")
    public String tags (@PathVariable("name") String name, Model model){
        List<TagDTO> tagsForSelectedAttraction = touristAttractionService.getTagsForSelectedAttraction(name);
        TouristAttractionDTO touristAttraction = touristAttractionService.getTouristAttractionByName(name);
        model.addAttribute("name", touristAttraction);
        model.addAttribute("tagsForSelectedAttraction", tagsForSelectedAttraction);
        return "tags";
    }

   @GetMapping("/add")
    public String addAttraction(Model model) throws SQLException {
        List<String> tagsList = touristAttractionService.getTags();
        List<String> cities = touristAttractionService.getCities();
        model.addAttribute("attraction", new TouristAttraction());
        model.addAttribute("tags", tagsList);
        model.addAttribute("cities", cities);
        return "addAttraction";
    }

   @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction newAttraction){
        touristAttractionService.addTouristAttraction(newAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable("name") String name, Model model) throws SQLException {
        TouristAttractionDTO touristAttraction = touristAttractionService.getTouristAttractionByName(name);
        List<String> tagsList = touristAttractionService.getTags();
        List<String> cityList = touristAttractionService.getCities();
        model.addAttribute("attraction", touristAttraction);
        model.addAttribute("tags", tagsList);
        model.addAttribute("cities", cityList);
        return "update";
    }

   @PostMapping("/update")
    public String saveUpdate(@ModelAttribute TouristAttraction updatedAttraction){
        touristAttractionService.updateTouristAttraction(updatedAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable("name") String name){
        touristAttractionService.deleteTouristAttraction(name);
        return "redirect:/attractions";
    }

}
