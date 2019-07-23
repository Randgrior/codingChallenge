package service;



import dto.CityResource;

import java.util.List;

public interface CityService {
    List<CityResource> findAllCities();
}
