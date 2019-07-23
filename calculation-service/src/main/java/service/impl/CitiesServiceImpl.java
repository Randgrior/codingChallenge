package service.impl;

import dto.CityResource;
import dto.CityRouteResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import service.CitiesService;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class CitiesServiceImpl implements CitiesService {
    private final RestTemplate restTemplate;

    @Autowired
    public CitiesServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CityRouteResource> getAll() {
        List response = restTemplate.getForEntity("http://localhost:8080/city/routes", List.class)
                .getBody();
        List<CityRouteResource> cityRouteResources = new LinkedList<>();
        for (Object res : response) {
            cityRouteResources.add(toResource((LinkedHashMap) res));
        }
        return cityRouteResources;
    }

    private CityRouteResource toResource(LinkedHashMap map){
        CityRouteResource cityRouteResource = new CityRouteResource();
        Object[] list = map.values().toArray();
        cityRouteResource.setCityRouteId(new Long(list[0].toString()));
        cityRouteResource.setDepartureCity(toCityResource((LinkedHashMap) list[1]));
        cityRouteResource.setDestinationCity(toCityResource((LinkedHashMap) list[2]));
        cityRouteResource.setDepartureTime(LocalTime.parse((String) list[3]));
        cityRouteResource.setArrivalTime(LocalTime.parse((String) list[4]));

        return  cityRouteResource;
    }

    private CityResource toCityResource(LinkedHashMap map) {
        CityResource cityResource = new CityResource();
        Object[] list = map.values().toArray();
        cityResource.setCityId(new Long(list[0].toString()));
        cityResource.setCity((String) list[1]);
        return cityResource;
    }
}
