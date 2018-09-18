package site.hhsa.demo.organizations.models;


import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String description;

    @Column(name = "date_time", nullable = false)
    private String dateTime;

    @Column(name = "str_addr", nullable = false)
    private String strAddr;

    @Column(name="zip_code", nullable = false)
    private String zipCode;

    public Event() {
    }

    public Event(String description, String dateTime, String strAddr, String zipCode) {
        this.description = description;
        this.dateTime = dateTime;
        this.strAddr = strAddr;
        this.zipCode = zipCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStrAddr() {
        return strAddr;
    }

    public void setStrAddr(String strAddr) {
        this.strAddr = strAddr;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
