package service;


import dto.CityRouteResource;

import java.util.List;

public interface CityRouteService {
    List<CityRouteResource> findAllByDepartureCity(String city);

    List<CityRouteResource> findAllByDestinationCity(String city);

    List<CityRouteResource> findAll();
}
