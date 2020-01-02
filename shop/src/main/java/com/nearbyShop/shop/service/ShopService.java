package com.nearbyShop.shop.service;

import com.nearbyShop.shop.dto.ShopDTO;

import java.util.List;

public interface ShopService {
     public List<ShopDTO> getNearbyShops(double lat, double lon, long radius);
}
