package service.impl;

import dao.CityRepository;
import domain.City;
import dto.CityResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CityService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityResource> findAllCities() {
        return cityRepository
                .findAll()
                .stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    private CityResource toResource(City city) {
        CityResource cityResource = new CityResource();
        cityResource.setCityId(city.getId());
        cityResource.setCity(city.getCity());
        return cityResource;
    }
}
