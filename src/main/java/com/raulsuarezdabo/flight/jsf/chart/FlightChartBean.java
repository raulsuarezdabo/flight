package com.raulsuarezdabo.flight.jsf.chart;

import com.raulsuarezdabo.flight.service.FlightService;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
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
        
        ChartSeries flights = new ChartSeries();
        flights.setLabel(
            FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                FacesContext.getCurrentInstance(), "msg").getString("numberFlights")
        );
        Iterator results = this.flightService.getChart().iterator();
        while(results.hasNext()) {
            Object [] tuple = (Object [])results.next();
            Long number = (Long)tuple[0];
            Timestamp time = (Timestamp)tuple[1];
            Date date = new Date(time.getTime());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            flights.set(df.format(date), number);
        }

        model.addSeries(flights);

        return model;
    }

    private void createAnimatedModels() {
        animatedModel2 = initBarModel();
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }
}
