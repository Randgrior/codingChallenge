package service.impl;

import builder.CustomGraphBuilder;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.problem.SearchProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CalculationService;
import service.CitiesService;

import java.util.LinkedList;
import java.util.List;

@Service
public class CalculationServiceImpl implements CalculationService {
    private final CitiesService citiesService;

    private final HipsterDirectedGraph<String, Long> graph;

    @Autowired
    public CalculationServiceImpl(CitiesService citiesService) {
        this.citiesService = citiesService;
        graph = new CustomGraphBuilder().createGraph(citiesService.getAll());
    }

    private List<String> calculateShortestTime(String departureCity, String destinationCity){
        SearchProblem problem = buildSearchProblem(departureCity);
        return (LinkedList<String> )Hipster.createDijkstra(problem).search(destinationCity).getOptimalPaths().get(0);
    }

    private List<String> calculateShortestWay(String departureCity, String destinationCity){
        SearchProblem problem = buildSearchProblem(departureCity);
        return (LinkedList<String> )Hipster.createBreadthFirstSearch(problem).search(destinationCity).getOptimalPaths().get(0);
    }

    private SearchProblem buildSearchProblem(String departureCity){
        return GraphSearchProblem
                .startingFrom(departureCity)
                .in(graph)
                .takeCostsFromEdges()
                .build();

    }

    @Override
    public List<String> calculate(String type, String departureCity, String destinationCity) {
        if (type.equalsIgnoreCase("less time")) {
            return calculateShortestTime(departureCity, destinationCity);
        } else {
            return calculateShortestWay(departureCity, destinationCity);
        }
    }
}
