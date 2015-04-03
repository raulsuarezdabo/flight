
package com.raulsuarezdabo.flight.jsf.user;

import com.mycompany.flight.service.UserService;
import com.mycompany.flight.utils.Utils;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import com.raulsuarezdabo.flight.entity.RoleEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.service.CityService;
import com.raulsuarezdabo.flight.service.CountryService;
import com.raulsuarezdabo.flight.service.RoleService;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Manage the UI for the user management
 * @author raulsuarez
 */
@ManagedBean(eager = true)
@ViewScoped
public class AddUserBean {

    /**
     * Name of the user
     */
    private String name;
    /**
     * Surname of the user
     */
    private String surname;
    /**
     * email of the user
     */
    private String email;
    /**
     * birthday of the user
     */
    private Date birthday;

    /**
     * phone of the user
     */
    private String phone;

    /**
     * nif of the user
     */
    private String nif;

    /**
     * address of the user
     */
    private String address;

    /**
     * country of the user
     */
    private String country;

    /**
     * city of the user
     */
    private int city;
    
    /**
     * role of the user
     */
    private List<Integer> role;

    /**
     * User service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    /**
     * Country service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;

    /**
     * City service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    
    @Autowired
    @ManagedProperty(value = "#{roleService}")
    private RoleService roleService;
    
    /**
     * List of countries
     */
    private List<CountryEntity> countries;

    /**
     * List of cities
     */
    private List<CityEntity> cities;
    
    /**
     * List of roles
     */
    private List<RoleEntity> roles;

    /**
     * Getter of name
     *
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     *
     * @param name name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of surname
     *
     * @return String
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter of surname
     *
     * @param surname surname of the user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter of the user
     *
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of the email
     *
     * @param email email of the user
     */
    public void setEmail(String email) {
        if (this.userService.getByEmail(email) == null) {
            this.email = email;
        } else {
            // Bring the error message using the Faces Context
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                    getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("emailExist");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("userForm:userEmail", message);
        }
    }

    /**
     * Getter of the birthday
     *
     * @return
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Setter of the birthday
     *
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Getter of the phone
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter of the phone
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter of the nif
     *
     * @return
     */
    public String getNif() {
        return nif;
    }

    /**
     * Setter of the nif
     *
     * @param nif
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Getter of the address
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter of the address
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter country
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter country
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter list of countries
     *
     * @return
     */
    public List<CountryEntity> getCountries() {
        if (this.countries == null) {
            this.countries = this.countryService.getAll();
        }
        return this.countries;
    }

    /**
     * Setter countries
     *
     * @param countries
     */
    public void setCountries(List<CountryEntity> countries) {
        this.countries = countries;
    }

    /**
     * Getter Cities
     *
     * @return
     */
    public List<CityEntity> getCities() {
        if (this.country != null) {
            this.cities = this.cityService.getCitiesByCountry(Utils.getCountryFromList(this.country, this.countries));
        } else {
            this.cities = null;
        }
        return this.cities;
    }

    /**
     * Setter of cities
     *
     * @param cities
     */
    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }

    /**
     * Getter city
     *
     * @return CityEntity
     */
    public int getCity() {
        return city;
    }

    /**
     * Setter city
     *
     * @param city
     */
    public void setCity(int city) {
        this.city = city;
    }

    /**
     * Getter of the userService
     *
     * @return
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Setter of the userService
     *
     * @param userService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Getter countryService
     *
     * @return
     */
    public CountryService getCountryService() {
        return countryService;
    }

    /**
     * Setter of the countryService
     *
     * @param countryService
     */
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Getter cityService
     * @return  CityService
     */
    public CityService getCityService() {
        return cityService;
    }

    /**
     * Setter cityService
     * @param cityService   CityService 
     */
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Getter role service
     * @return  RoleService
     */
    public RoleService getRoleService() {
        return roleService;
    }

    /**
     * Setter of role service
     * @param roleService   RoleService
     */
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Getter role
     * @return  List of integers
     */
    public List<Integer> getRole() {
        return role;
    }

    /**
     * Setter role
     * @param role  List of integers with the roles ids 
     */
    public void setRole(List<Integer> role) {
        this.role = role;
    }

    /**
     * Getter roles
     * @return  List of roles
     */
    public List<RoleEntity> getRoles() {
        if (this.roles == null) {
            this.roles = this.roleService.getAll();
        }
        return this.roles;
    }

    /**
     * Setter roles
     * @param roles List 
     */
    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
    
    public String addUserAction() {
        try {

            UserEntity user = this.userService.addUser(
                    this.email,
                    this.name,
                    this.surname,
                    this.address,
                    this.nif,
                    this.phone,
                    this.birthday,
                    Utils.getCountryFromList(this.country, this.countries),
                    Utils.getCityFromList(this.city, this.cities),
                    Utils.getRolesFromList(this.role, this.roles)
            );
            if (user != null && user instanceof UserEntity) {
                    FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.SUCCESS,
                            FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                                    FacesContext.getCurrentInstance(), "msg").getString("addSuccessUserMessage")
                    );
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER,
                            FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                                    FacesContext.getCurrentInstance(), "msg").getString("addDangerUserMessage")
                    );
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/user/index?faces-redirect=true";
    }
    
}
