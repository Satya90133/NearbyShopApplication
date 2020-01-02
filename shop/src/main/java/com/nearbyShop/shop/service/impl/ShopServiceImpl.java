package com.nearbyShop.shop.service.impl;

import com.nearbyShop.shop.dto.GoogleApis.Result;
import com.nearbyShop.shop.dto.ShopDTO;
import com.nearbyShop.shop.dto.GoogleApis.ShopListDTO;
import com.nearbyShop.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private String apiKey = "k9fpNAlSWSKYn7duXFrIALVJGFRQczA2";     // my TomTom/Google search api key
    private String keyWord = "shop";                                // search key word

    @Autowired
    RestTemplate restTemplate; // to communicate with services registered with eureka

    RestTemplate restTemplateTom = new RestTemplate(); // to communicate with TomTom api

    @Override
    public List<ShopDTO> getNearbyShops(double lat, double lon, long radius) {
        //TODO develop with google api
        String query = String.format("https://api.tomtom.com/search/2/search/%s.json?key=%s&lat=%f&lon=%f&radius=%d",
                                     //google api https://maps.googleapis.com/maps/api/staticmap?center=40.714%2c%20-73.998&zoom=12&size=400x400&key=YOUR_API_KEY
                keyWord,
                apiKey,
                lat,
                lon,
                radius);

        ShopListDTO results = restTemplateTom.getForObject(query,ShopListDTO.class);
        List<ShopDTO> shops = buildData(results);
        return shops;
    }

    private List<ShopDTO> buildData(ShopListDTO shopListDTO) {
       //TODO: Need to map the data with DTO
        List<ShopDTO> shops = new ArrayList<>();

        for(Result result : shopListDTO.getResults()){
            ShopDTO shop = new ShopDTO();

            shop.setShop_id(result.getShop_id());
            shop.setName(result.getDetails().getName());
            shop.setPhone(result.getDetails().getPhone());
            shop.setDist(result.getDistance());
            shop.setLat(result.getPosition().getLat());
            shop.setLon(result.getPosition().getLon());
            shop.setAddress(result.getAddress().getAddress());
            shop.setCategories(result.getDetails().getCategories());
            shops.add(shop);
        }
        return shops;
    }
}
