package nearbyshops.user.user.controller;

import io.swagger.models.Model;
import nearbyshops.user.user.DTO.ShopDTO;
import nearbyshops.user.user.User;
import nearbyshops.user.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    RestTemplate restTemplate;



    @GetMapping("/")
    public String getIndex(@RequestParam(name="lat",required = false) Double lat, @RequestParam(name="lon",required = false) Double lon, Model model) {

        if(lat==null || lon==null){
            model.addAttribute("positionExist",false);
            return "index"
        }

        listNearbyShops(lat,lon,model);
        listPreferredShops(model);
        return "index";

    }




    @GetMapping("/nearbyShops")
    public String listNearbyShops(Double lat,Double lon,Model model){
        //all the nearby shops from the shopService
        String url = "http://localhost:9001/$lat=" + lat + "$lon=" + lon +"$radius=1000/nearbyShops";   // radius == 1000 m
        ResponseEntity<List<ShopDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ShopDTO>>() {});
        List<ShopDTO> shops = responseEntity.getBody();
        // eliminating preferred and disliked shops
        shops = userService.filterUserNearbyShops(user,shops);
        //TODO:// the list of shops to display
        //TODO:// the model which will be sent by the form to add a new preferred/disliked shop
        return null;
    }



}