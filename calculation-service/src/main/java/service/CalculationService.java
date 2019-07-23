package service;

import java.util.List;

public interface CalculationService {
    List<String> calculate(String type, String departureCity, String destinationCity);
}
