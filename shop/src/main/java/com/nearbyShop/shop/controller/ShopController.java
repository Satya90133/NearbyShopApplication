package com.nearbyShop.shop.controller;

import com.nearbyShop.shop.dto.ShopDTO;
import com.nearbyShop.shop.service.ShopService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/$lat={lat}$lon={lon}$radius={radius}/nearbyShops")
    @ApiOperation("Get the list of nearby shops")
    public List<ShopDTO> getNearByShops(@PathVariable("lat") double lat,
                                        @PathVariable("lon") double lon,
                                        @PathVariable("radius") long radius) {
        return shopService.getNearbyShops(lat, lon, radius);
    }
}
