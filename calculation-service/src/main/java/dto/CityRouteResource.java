package dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CityRouteResource {

    private Long cityRouteId;

    private CityResource departureCity;

    private CityResource destinationCity;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

}
