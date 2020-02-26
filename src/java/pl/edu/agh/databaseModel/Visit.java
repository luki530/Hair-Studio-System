package pl.edu.agh.databaseModel;

import pl.edu.agh.persistence.Persistable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "visits")
@NamedQuery(name = "Visit.findAll", query = "SELECT v FROM Visit v")
public class Visit implements Persistable {
    private Integer id;
    private Timestamp startTime;
    private Timestamp endTime;
    private String service;
    private boolean confirmed;
    private boolean busy;
    private User usersByCreator;
    private User usersByEmployee;
    private User usersByClient;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @JoinColumn(name = "service")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Basic
    @Column(name = "confirmed")
    public boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Basic
    @Column(name = "busy")
    public boolean getBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(id, visit.id) &&
                Objects.equals(startTime, visit.startTime) &&
                Objects.equals(endTime, visit.endTime) &&
                Objects.equals(service, visit.service) &&
                Objects.equals(confirmed, visit.confirmed) &&
                Objects.equals(busy, visit.busy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, service, confirmed, busy);
    }

    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "login")
    public User getUsersByCreator() {
        return usersByCreator;
    }

    public void setUsersByCreator(User usersByCreator) {
        this.usersByCreator = usersByCreator;
    }

    @ManyToOne
    @JoinColumn(name = "employee", referencedColumnName = "login", nullable = false)
    public User getUsersByEmployee() {
        return usersByEmployee;
    }

    public void setUsersByEmployee(User usersByEmployee) {
        this.usersByEmployee = usersByEmployee;
    }

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "login")
    public User getUsersByClient() {
        return usersByClient;
    }

    public void setUsersByClient(User usersByClient) {
        this.usersByClient = usersByClient;
    }
}
