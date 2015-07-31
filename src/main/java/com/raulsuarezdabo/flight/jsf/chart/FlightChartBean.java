package com.raulsuarezdabo.flight.jsf.chart;

import com.raulsuarezdabo.flight.service.FlightService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
@ManagedBean
public class FlightChartBean {

    private BarChartModel animatedModel2;
    
    /**
     * Airport service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{flightService}")
    private FlightService flightService;

    /**
     * Creates a new instance of FlightChartBean
     */
    public FlightChartBean() {
    }
    
    /**
     * Getter flightService
     * @return  FlightService
     */
    public FlightService getFlightService() {
        return flightService;
    }

    /**
     * Setter flightService
     * @param flightService FlightService 
     */
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void createAnimatedModels() {
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Numero de vuelos");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }
}
