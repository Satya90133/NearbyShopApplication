package com.nearbyShop.shop.entity;

import java.util.List;

//https://developers.google.com/maps/documentation/directions/web-service-best-practices   Google api
public class Shop {
    //https://maps.googleapis.com/maps/api/staticmap?center=40.714%2c%20-73.998&zoom=12&size=400x400&key=YOUR_API_KEY
    private String shop_id; // id provided by the TomTom/Google search API (unstable)
    private String name;
    private String phone;
    private String url;
    private double score;
    private double dist;
    private double lat;   // latitude
    private double lon;   // longitude
    private String address;
    private List<String> categories;
}
