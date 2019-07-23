package controller;

import dto.CityResource;
import dto.CityRouteResource;
import exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CityRouteService;
import service.CityService;

import java.util.List;

@Api(value = "City controller", description = "provides cities and routes getting endpoints")
@Controller
@RequestMapping("city")
@CrossOrigin("{*}")
public class CityController {
    private static Logger logger = LoggerFactory.getLogger(CityController.class);

    private final CityService cityService;

    private final CityRouteService cityRouteService;

    @Autowired
    public CityController(
            CityService cityService,
            CityRouteService cityRouteService) {
        this.cityService = cityService;
        this.cityRouteService = cityRouteService;
    }

    @ApiOperation(value = "Finds all available cities ")
    @GetMapping("")
    public ResponseEntity<List<CityResource>> getAllCities(){
        logger.info("get all cities");
        return new ResponseEntity<>(cityService.findAllCities(), HttpStatus.OK);
    }

    @ApiOperation(value = "Finds all routes between cities by departure city")
    @GetMapping("/{departure_city}")
    public ResponseEntity<List<CityRouteResource>> getAllByDepartureCity(
            @PathVariable("departure_city") String departureCity){
        logger.info("get all routes by departure city ");
        return new ResponseEntity<>(cityRouteService.findAllByDepartureCity(departureCity),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Finds all routes between cities by destination city")
    @GetMapping("/{destination_city}")
    public ResponseEntity<List<CityRouteResource>> getAllByDestinationCity(
            @PathVariable("destination_city") String destinationCity){
        logger.info("get all routes by destination city ");
        return new ResponseEntity<>(cityRouteService.findAllByDestinationCity(destinationCity),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Finds all routes")
    @GetMapping("/routes")
    public ResponseEntity<List<CityRouteResource>> getAllRoutes(){
        logger.info("get all routes ");
        return new ResponseEntity<>(cityRouteService.findAll(),
                HttpStatus.OK);
    }

}
