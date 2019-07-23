package builder;

import dto.CityRouteResource;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;

import java.time.temporal.ChronoField;
import java.util.List;

public class CustomGraphBuilder {
    private HipsterDirectedGraph<String,Long> graph;

    public HipsterDirectedGraph<String,Long> createGraph(List<CityRouteResource> routeResources){
        GraphBuilder<String, Long> builder = GraphBuilder.create();
        routeResources.forEach(cityRouteResource ->
            builder.connect(cityRouteResource.getDepartureCity().getCity())
                    .to(cityRouteResource.getDestinationCity().getCity())
                    .withEdge(cityRouteResource.getArrivalTime()
                            .minusNanos(cityRouteResource.getDepartureTime()
                                    .toNanoOfDay()).getLong(ChronoField.SECOND_OF_DAY)));
        graph = builder.createDirectedGraph();
        return graph;
    }
}
