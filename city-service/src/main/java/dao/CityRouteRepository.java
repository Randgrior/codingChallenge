package dao;

import domain.CityRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRouteRepository extends JpaRepository<CityRoute, Long> {

    List<CityRoute> findAllByDepartureCity(String departureCity);

    List<CityRoute> findAllByDestinationCity(String destinationCity);
}
