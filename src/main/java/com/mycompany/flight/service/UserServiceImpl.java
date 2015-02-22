package com.mycompany.flight.service;

import com.mycompany.flight.service.EmailServiceImpl;
import com.mycompany.flight.dao.UserDAOImpl;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import com.raulsuarezdabo.flight.entity.RoleEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import com.raulsuarezdabo.flight.service.RoleService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author raulsuarez
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDAOImpl userDAO;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmailServiceImpl email;
    
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
     * @param name
     * @param surname
     * @param address
     * @param nif
     * @param phone
     * @param birthday
     * @param country
     * @param locale
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
            CityEntity city,
            Locale locale
    ) {
        try {
            ArrayList to = new ArrayList();

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
            // mark the Date on create an user
            java.util.Date now = new Date();
            user.setCreatedAt(new java.sql.Date(now.getTime()));
            RoleEntity userRole = this.roleService.getUserRole();
            if (userRole == null) {
                throw new Exception("Error creating the user role");
            }
            user.addRole(userRole);
            this.userDAO.addUser(user);

            to.add(user);

            this.email.sendMail(to, null, "wellcome", locale);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method to encrypt the password the user
     *
     * @param original
     * @return String codified to md5
     */
    private String get_md5(String original) {
        String md5 = "";
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

    /**
     * Method for updating the information of an specific user
     * @param email
     * @param editUser
     * @param persist
     * @return 
     */
    @Override
    @Transactional
    public UserEntity updateUser(String email, UserEntity editUser, boolean persist) {
        try {
            UserEntity user = this.getByEmail(email);
            if (user != null && user instanceof UserEntity) {
                // Handler block
                if ( editUser.getName() != null ) {
                    user.setName(editUser.getName());    
                }
                if ( editUser.getSurname() != null ) {
                    user.setSurname(editUser.getSurname());
                }
                if ( editUser.getBirthDay() != null ) {
                    user.setBirthDay(new java.sql.Date(editUser.getBirthDay().getTime()));
                }
                if ( editUser.getNif() != null ) {
                    user.setNif(editUser.getNif());
                }
                if ( editUser.getPhone() != null ) {
                    user.setPhone(editUser.getPhone());
                }
                if ( editUser.getAddress() != null ) {
                    user.setAddress(editUser.getAddress());
                }
                if ( editUser.getCountry() != null ) {
                    user.setCountry(editUser.getCountry());
                }
                if ( editUser.getCity() != null ) {
                    user.setCity(editUser.getCity());
                }
                // Persist block
                if (persist == true) {
                    if ( this.userDAO.updateUser(user) == false) {
                        throw new Exception("Error updating the user");
                    }
                }
            }
            return user;
        } catch( Exception e ) {
            System.out.println(e.getMessage());
            return null;
        }
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
        return this.userDAO.findByEmail(email);
    }

    /**
     *
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = this.userDAO.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " not found!");
        }
        return user;
    }

    @Override
    public Boolean forgotAccount(String email, Locale current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * method that check if the given email and password match with a created
     * account or not
     *
     * @param email
     * @param password
     * @return boolean
     */
    @Override
    @Transactional
    public boolean checkCredentails(String email, String password) {
        try {
            UserEntity user = this.getByEmail(email);
            if (user == null) {
                throw new Exception("User not found");
            }
            if (user.getPassword().compareTo(password) != 0) {
                throw new Exception("User password not match");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method to know if currently is a user logged
     * @return boolean
     */
    @Override
    public boolean isLogged() {
        try {
            return (this.getLoggedUser()) instanceof UserEntity;
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * Method that obtains the currently logged user, if not returns null
     * @return UserEntity
     */
    @Override
    public UserEntity getLoggedUser () {
        try {
            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return this.getByEmail(user.getEmail());
        } catch ( Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Method that clears the current user's session
     * @return boolean
     */
    @Override
    public boolean logout () {
        try {
            SecurityContextHolder.clearContext();
            return true;
        } catch ( Exception e ) {
            return false;
        }
    }
}
