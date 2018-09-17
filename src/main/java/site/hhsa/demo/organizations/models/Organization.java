package site.hhsa.demo.organizations.models;

import javax.persistence.*;

@Entity
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(name = "org_name", nullable = false)
    private String orgName;

    @Column(name="str_addr", nullable = false)
    private String strAddr;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(name="zip_code", nullable = false)
    private String zipCode;

    @Column(name = "tax_id", nullable = false)
    private String taxId;

    @Column(name="phn_number", nullable = false)
    private String phNum;

    @Column(name="is_validated", nullable = false)
    private String isValidated;

    @Column(name="creator_firstname", nullable = false)
    private String creatorFirstName;

    @Column(name="creator_lastname", nullable = false)
    private String creatorLastName;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = " date_created DATE NOT NULL DEFAULT NOW()")
    private String dateCreated;

    public Organization() {
    }

    public Organization(String email, String orgName, String strAddr, String city, String state, String zipCode, String taxId, String phNum, String isValidated, String creatorFirstName, String creatorLastName, String password, String dateCreated) {
        this.email = email;
        this.orgName = orgName;
        this.strAddr = strAddr;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.taxId = taxId;
        this.phNum = phNum;
        this.isValidated = isValidated;
        this.creatorFirstName = creatorFirstName;
        this.creatorLastName = creatorLastName;
        this.password = password;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStrAddr() {
        return strAddr;
    }

    public void setStrAddr(String strAddr) {
        this.strAddr = strAddr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getPhNum() {
        return phNum;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
    }

    public String getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(String isValidated) {
        this.isValidated = isValidated;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCreatorFirstName() {
        return creatorFirstName;
    }

    public void setCreatorFirstName(String creatorFirstName) {
        this.creatorFirstName = creatorFirstName;
    }

    public String getCreatorLastName() {
        return creatorLastName;
    }

    public void setCreatorLastName(String creatorLastName) {
        this.creatorLastName = creatorLastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
