package com.mycompany.flight.service;

import com.mycompany.flight.dao.RoleDAOImpl;
import com.mycompany.flight.dao.UserDAOImpl;
import com.mycompany.flight.entity.CityEntity;
import com.mycompany.flight.entity.CountryEntity;
import com.mycompany.flight.entity.RoleEntity;
import com.mycompany.flight.entity.UserEntity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOImpl userDAO;
    
    @Autowired
    private RoleDAOImpl roleDAO;

    /**
     * property that contains the user to manage
     */
    private UserEntity user;

    /**
     * default constructor
     */
    public UserServiceImpl() {
        this.user = null;
    }

    /**
     * constructor with parameters
     *
     * @param user
     */
    public UserServiceImpl(UserEntity user) {
        this.user = user;
    }

    public boolean persist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param email
     * @param password
     * @param name
     * @param surname
     * @param address
     * @param nif
     * @param phone
     * @param birthday
     * @param country
     * @param city
     * @return new User entity persisted or not depending on the persist @param
     */
    @Override
    @Transactional
    public UserEntity newUser(
            String email, 
            String name, 
            String surname, 
            String address, 
            String nif, 
            String phone, 
            Date birthday, 
            CountryEntity country, 
            CityEntity city
    ) {
        try {
            user = new UserEntity();
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setAddress(address);
            user.setNif(nif);
            user.setPhone(phone.trim());
            user.setBirthDay(new java.sql.Date(birthday.getTime()));
            user.setCountry(country);
            user.setCity(city);
            RoleEntity userRole = this.roleDAO.findById(RoleEntity.USER_ROLE);
            this.userDAO.addUser(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method to encrypt the password the user
     * @param original
     * @return String codified to md5 
     */
    private String get_md5(String original){
        String md5="";
        try {
            if (!original.equalsIgnoreCase("")) {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.reset();
                md.update(original.getBytes());
                byte bytes[] = md.digest();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    String hex = Integer.toHexString(0xff & bytes[i]);
                    if (hex.length() == 1) {
                        sb.append('0');
                    }
                    sb.append(hex);
                }
                md5 = sb.toString();
            }
            return md5;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    @Override
    public UserEntity updateUser(UserEntity user, HashMap values, boolean persist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUser(UserEntity user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserEntity getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserEntity getByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
