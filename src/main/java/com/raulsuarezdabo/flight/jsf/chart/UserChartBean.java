/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.chart;

import com.mycompany.flight.service.UserService;
import com.raulsuarezdabo.flight.service.BookService;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
@ManagedBean
public class UserChartBean implements Serializable {

    private LineChartModel animatedModel1;
    
    /**
     * User service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{userService}")
    private UserService userService;
    
    /**
     * book service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{bookService}")
    private BookService bookService;
    
    /**
     * userSErvice Getter
     *
     * @return
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * userService Setter
     *
     * @param userService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Getter bookService
     * @return  BookService
     */
    public BookService getBookService() {
        return bookService;
    }

    /**
     * Setter bookService
     * @param bookService 
     */
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    
    /**
     * Getter animatedModel
     * @return  LineChartModel
     */
    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }
    
    /**
     * Setter animatedModel1
     * @param animatedModel1    LineChartModel
     */
    public void setAnimatedModel1(LineChartModel animatedModel1) {
        this.animatedModel1 = animatedModel1;
    }
    
    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        animatedModel1.setShowPointLabels(true);
        animatedModel1.getAxes().put(AxisType.X, new CategoryAxis(FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                FacesContext.getCurrentInstance(), "msg").getString("years")));
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
    
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        model.addSeries(
            this.createChartSeries(
                FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                FacesContext.getCurrentInstance(), "msg").getString("createdUsers"),
                this.userService.getChart()
            )
        );
        
        model.addSeries(
            this.createChartSeries(
                FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                FacesContext.getCurrentInstance(), "msg").getString("createdBooks"),
                this.bookService.getChart()
            )
        );
        
        return model;
    }
    
    /**
     * Method for create a new line on the chart
     * @param title
     * @param items
     * @return 
     */
    private ChartSeries createChartSeries(String title, List items) {
        ChartSeries chart = new ChartSeries();
        
        chart.setLabel(title);
        
        Iterator results = items.iterator();
        while(results.hasNext()) {
            Object [] tuple = (Object [])results.next();
            Long number = (Long)tuple[0];
            Timestamp time = (Timestamp)tuple[1];
            Date date = new Date(time.getTime());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            chart.set(df.format(date), number);
        }
        return chart;
    }
    
    @PostConstruct
    public void init() {
        createAnimatedModels();
    }
    
}
