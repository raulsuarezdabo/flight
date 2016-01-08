/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.dao.AirlineDAO;
import com.raulsuarezdabo.flight.entity.AirlineEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service that's implements all method defined on the interface
 * @author raulsuarez
 */
@Service
@Transactional
public class AirlineServiceImpl implements AirlineService {
    
    @Autowired
    private AirlineDAO airlineDAO;

    /**
     * Method for adding an aiport
     * @param name
     * @param code
     * @param country
     * @return 
     */
    @Override
    @Transactional
    public AirlineEntity addAirline(String name, String code, CountryEntity country) {
        try {
        AirlineEntity airline = new AirlineEntity();
        
        airline.setName(name);
        airline.setCode(code);
        airline.setCountry(country);
        this.airlineDAO.addAirline(airline);
        return airline;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method for update the airline information
     * @param id    int
     * @param airline   AirlineEntity   Information to update
     * @param update    boolean indicates if persist or not
     * @return AirlineEntity    airline updated or null if some problem
     */
    @Override
    @Transactional
    public AirlineEntity updateAirline(int id, AirlineEntity airline, boolean update) {
        try {
            AirlineEntity airlineToUpdate = this.airlineDAO.findById(id);
            airlineToUpdate.setName(airline.getName());
            airlineToUpdate.setCode(airline.getCode());
            airlineToUpdate.setCountry(airline.getCountry());
            if (this.airlineDAO.updateAirline(airlineToUpdate) == false) {
                throw new Exception("Error updating the airline");
            }
            return airlineToUpdate;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    /**
     * Method to delete an airline
     * @param id    int identifier of the aiport
     * @return boolean  if it's success or not
     */
    @Override
    @Transactional
    public boolean deleteAirline(int id) {
        try {
            AirlineEntity airline = this.airlineDAO.findById(id);
            if (airline == null) {
                throw new Exception("Not found airline");
            }
            return this.airlineDAO.deleteAirline(airline);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }

    /**
     * Get all airlines available
     * @return List all airlines listed
     */
    @Override
    public List<AirlineEntity> getAll() {
        try {
            List<AirlineEntity> airlines = this.airlineDAO.findAll();
            return airlines;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Get an airline by code
     * @param code  String  code
     * @return  AirlineEntity   airline
     */
    @Override
    public AirlineEntity getByCode(String code) {
        return this.airlineDAO.findByCode(code);
    }
    
    /**
     * Get an airline by Id
     * @param id
     * @return  AirlineEntity   airline
     */
    @Override
    public AirlineEntity getById(int id) {
        return this.airlineDAO.findById(id);
    }
    
}
