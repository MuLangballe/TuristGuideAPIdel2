package com.example.turistguideapidel2.controller;

import com.example.turistguideapidel2.model.TouristAttraction;
import com.example.turistguideapidel2.service.TouristAttractionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristAttractionController {
    TouristAttractionService touristAttractionService;

    public TouristAttractionController(TouristAttractionService touristAttractionService){
        this.touristAttractionService = touristAttractionService;
    }

    //TODO: GET ("/attractions")
    @GetMapping("")
    public String showAttractions(Model model){
        List<TouristAttraction> attractionList = touristAttractionService.getListOfAttractions();
        model.addAttribute("attractions", attractionList);
        return "attractionList";
    }

    //TODO: GET ("/attractions/{name}/tags")
    @GetMapping("/{name}/tags")
    public String tags (@PathVariable("name") String name, Model model){
        List<String> tagsForSelectedAttraction = touristAttractionService.getTagsForSelectedAttraction(name);
        TouristAttraction touristAttraction = touristAttractionService.getTouristAttractionByName(name);
        model.addAttribute("name", touristAttraction);
        model.addAttribute("tagsForSelectedAttraction", tagsForSelectedAttraction);
        return "tags";
    }

    //TODO: GET ("/attractions/add")
   @GetMapping("/tilføj")
    public String addAttraction(Model model){
        model.addAttribute("attraction", new TouristAttraction());
        List<String> tagsList = touristAttractionService.getTags();
        model.addAttribute("tags", tagsList);
        return "addAttraction";
    }

    //TODO: POST ("/attractions/save")
    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction newAttraction){
        touristAttractionService.addTouristAttraction(newAttraction);
        return "redirect:/attractionList";
    }

    //TODO: GET ("/attractions/{name}/edit")

    //TODO: POST ("/attractions/update")

    //TODO: GET ("/attractions/{name}/delete")

}
