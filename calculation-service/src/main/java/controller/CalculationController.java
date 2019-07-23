package controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CalculationService;

import java.util.List;


@Controller
@RequestMapping("calculation")
@CrossOrigin("{*}")
@Api(value = "Calculation controller", description = "provides route calculation endpoint")
public class CalculationController {
    private static Logger logger = LoggerFactory.getLogger(CalculationController.class);


    private final CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @ApiOperation(value = "Finds the best route between two cities, type should be \"less time\" or \"less cities\" ")
    @GetMapping("")
    public ResponseEntity<List<String>> calculate(@RequestParam String type,
                                                  @RequestParam String departureCity,
                                                  @RequestParam String destinationCity) {
        logger.info("Calculate best route by type");
        return new ResponseEntity<>(calculationService.calculate(type,
                departureCity, destinationCity), HttpStatus.OK);
    }
}
