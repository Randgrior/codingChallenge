package service.impl;

import dao.CityRepository;
import dao.CityRouteRepository;
import domain.City;
import domain.CityRoute;
import dto.CityResource;
import dto.CityRouteResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CityRouteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityRouteServiceImpl implements CityRouteService {

private final CityRouteRepository cityRouteRepository;
    private final CityRepository cityRepository;

    @Autowired
    public CityRouteServiceImpl(CityRouteRepository cityRouteRepository,
                                CityRepository cityRepository) {
        this.cityRouteRepository = cityRouteRepository;
        this.cityRepository = cityRepository;
    }

    public List<CityRouteResource> findAllByDepartureCity(String city){
        return cityRouteRepository.findAllByDepartureCity(
                cityRepository.findFirstByCity(city).getCity())
                .stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public List<CityRouteResource> findAllByDestinationCity(String city){
        return cityRouteRepository.findAllByDestinationCity(
                cityRepository.findFirstByCity(city).getCity())
                .stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    @Override
    public List<CityRouteResource> findAll() {
        return cityRouteRepository.findAll()
                .stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    private CityRouteResource toResource(CityRoute cityRoute){
        CityRouteResource cityRouteResource = new CityRouteResource();
        cityRouteResource.setCityRouteId(cityRoute.getId());
        cityRouteResource.setDepartureCity(toResource(cityRoute.getDepartureCity()));
        cityRouteResource.setDestinationCity(toResource(cityRoute.getDestinationCity()));
        cityRouteResource.setDepartureTime(cityRoute.getDepartureTime());
        cityRouteResource.setArrivalTime(cityRoute.getArrivalTime());
        return cityRouteResource;
    }

    private CityResource toResource(City city) {
        CityResource cityResource = new CityResource();
        cityResource.setCityId(city.getId());
        cityResource.setCity(city.getCity());
        return cityResource;
    }
}
