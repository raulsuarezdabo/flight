/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.jsf.airplane;

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import com.raulsuarezdabo.flight.service.AirplaneService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class AirplaneListBean {

    /**
     * Airline service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{airplaneService}")
    private AirplaneService airplaneService;
    
    /**
     * Airline list
     */
    private List<AirplaneEntity> airplanes;
    
    /**
     * Airline filtered
     */
    private List<AirplaneEntity> filteredAirplane;
    
    /**
     * Creates a new instance of AirplaneListBean
     */
    public AirplaneListBean() {
    }

    /**
     * Getter airplaneService
     * @return  AirplaneService
     */
    public AirplaneService getAirplaneService() {
        return airplaneService;
    }

    /**
     * Setter airplaneService
     * @param airplaneService   AirplaneService 
     */
    public void setAirplaneService(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    /**
     * Getter airplanes list
     * @return  List    airplanes's list
     */
    public List<AirplaneEntity> getAirplanes() {
        return airplanes;
    }

    /**
     * Setter airplanes list
     * @param airplanes List
     */
    public void setAirplanes(List<AirplaneEntity> airplanes) {
        this.airplanes = airplanes;
    }

    /**
     * Getter filtered airplanes list
     * @return  List    filtered airplane list
     */
    public List<AirplaneEntity> getFilteredAirplane() {
        return filteredAirplane;
    }
    
    @PostConstruct
    public void init() {
        this.airplanes = this.airplaneService.getAll();
    }
    
}
