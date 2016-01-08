/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.mycompany.flight.service;

import com.mycompany.flight.dao.UserDAOImpl;
import com.raulsuarezdabo.flight.utils.Utils;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import com.raulsuarezdabo.flight.entity.RoleEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import com.raulsuarezdabo.flight.service.RoleService;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    public static int DAYS = 60;

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
            RoleEntity userRole = this.roleService.getUserRole();
            if (userRole == null) {
                throw new Exception("Error creating the user role");
            }
            user.addRole(userRole);
            String token = Utils.generateToken();
            user.setToken(token);
            this.userDAO.addUser(user);

            to.add(user);

            this.email.sendMail(to, this.prepareInfoForemail("wellcome", locale, user), "wellcome", locale);
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
     *
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
                if (editUser.getName() != null) {
                    user.setName(editUser.getName());
                }
                if (editUser.getSurname() != null) {
                    user.setSurname(editUser.getSurname());
                }
                if (editUser.getBirthDay() != null) {
                    user.setBirthDay(new java.sql.Date(editUser.getBirthDay().getTime()));
                }
                if (editUser.getNif() != null) {
                    user.setNif(editUser.getNif());
                }
                if (editUser.getPhone() != null) {
                    user.setPhone(editUser.getPhone());
                }
                if (editUser.getAddress() != null) {
                    user.setAddress(editUser.getAddress());
                }
                if (editUser.getCountry() != null) {
                    user.setCountry(editUser.getCountry());
                }
                if (editUser.getCity() != null) {
                    user.setCity(editUser.getCity());
                }
                if (editUser.getToken() != null) {
                    user.setToken(editUser.getToken());
                }
                if (editUser.getPassword() != null) {
                    user.setPassword(editUser.getPassword());
                }
                if (editUser.getRole() != null && editUser.getRole().isEmpty() == false) {
                    user.setRole(editUser.getRole());
                }
                // Persist block
                if (persist == true) {
                    if (this.userDAO.updateUser(user) == false) {
                        throw new Exception("Error updating the user");
                    }
                }
            }
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * method for deleting a user by id
     * @param id    int
     * @return  UserEntity
     */
    @Override
    public boolean deleteUser(int id) {
        try {
            UserEntity user = this.userDAO.findById(id);
            if (user == null) {
                throw new Exception("Not found user");
            }
            return this.userDAO.deleteUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Getter user id
     * @param id    int
     * @return  UserEntity
     */
    @Override
    public UserEntity getById(int id) {
        return this.userDAO.findById(id);
    }
    
    /**
     * Get all users on a list
     * @return  List of users
     */
    @Override
    public List <UserEntity> getAll() {
        return this.userDAO.getAllUsers();
    }

    /**
     * get by email a user
     * @param email String
     * @return  UserEntity
     */
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

    /**
     * Method for sending email recovery password
     * @param email String
     * @param current   Locale  current location
     * @return  Boolean Success sending e-mail or not
     */
    @Override
    public Boolean forgotAccount(String email, Locale current) {
        UserEntity user = this.userDAO.findByEmail(email);
        if (user != null && user instanceof UserEntity) {
            String token = Utils.generateToken();
            user.setToken(token);
            if (this.userDAO.updateUser(user)) {
                ArrayList to = new ArrayList();
                to.add(user);
                this.email.sendMail(to, this.prepareInfoForemail("forgot_account", current, user), "forgot_account", current);
                return true;
            }
        }
        return false;
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
     *
     * @return boolean
     */
    @Override
    public boolean isLogged() {
        try {
            return (this.getLoggedUser()) instanceof UserEntity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method that obtains the currently logged user, if not returns null
     *
     * @return UserEntity
     */
    @Override
    public UserEntity getLoggedUser() {
        try {
            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return this.getByEmail(user.getEmail());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method that clears the current user's session
     *
     * @return boolean
     */
    @Override
    public boolean logout() {
        try {
            SecurityContextHolder.clearContext();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method that prepares the information for the email
     *
     * @param wellcome type of e-mail
     * @param locale Location of the email (language)
     * @param user User model where comes the information
     * @return HasMap
     */
    private HashMap prepareInfoForemail(String type, Locale locale, UserEntity user) {
        try {
            String messages = null;
            if (locale.getLanguage().equals("es")) {
                messages = "com.mycompany.flight.messages";
            } else {
                messages = "com.mycompany.flight.messages_" + locale.getLanguage().toLowerCase();
            }
            ResourceBundle resource = ResourceBundle.getBundle(messages);
            HashMap map = new HashMap();
            map.put("wellcome", MessageFormat.format(resource.getString("wellcomeEmail"), user.getName()));
            if (type.compareTo("wellcome") == 0) {
                map.put("title", resource.getString("wellcomeEmailTitle"));
                map.put("wellcomeText", resource.getString("wellcomeText"));
                map.put("wellcomeExplanation", resource.getString("wellcomeExplanation"));
            }
            if (type.compareTo("forgot_account") == 0) {
                map.put("title", resource.getString("forgotEmailTitle"));
                map.put("forgotText", resource.getString("forgotText"));
                HashMap content = new HashMap();
                content.put("token", user.getToken());
                content.put("email", URLEncoder.encode(user.getEmail(), "UTF-8"));
                map.put("forgotLink", Utils.getUrl("register/recovery-password.xhtml", content));
                map.put("access", resource.getString("access"));
            }
            return map;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method for adding new user with a specific roles
     * @param email String
     * @param name  String
     * @param surname   String
     * @param address   String
     * @param nif   String
     * @param phone String
     * @param birthday  Date
     * @param country   CountryEntity
     * @param city  CityEntity
     * @param roles List of roles
     * @return  UserEntity
     */
    @Override
    @Transactional
    public UserEntity addUser(String email, String name, String surname, String address, String nif, String phone, Date birthday, CountryEntity country, CityEntity city, List<RoleEntity> roles) {
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

            user.setRole(roles);
            String token = Utils.generateToken();
            user.setToken(token);
            this.userDAO.addUser(user);
            return user;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Method for returning a report for chart
     * @return  List
     */
    @Override
    public List getChart() {
        try {
            return this.userDAO.findCountUsersByDate(UserServiceImpl.DAYS);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
