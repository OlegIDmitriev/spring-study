package com.apress.prospring5.ch11.entities;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.lang.annotation.Target;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LICENSE_PLATE")
    private String licensePlate;

    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @Column(name = "MANUFACTURE_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime manufacturerDate;

    @Column(name = "AGE")
    private int age;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public DateTime getManufacturerDate() {
        return manufacturerDate;
    }

    public void setManufacturerDate(DateTime manufacturerDate) {
        this.manufacturerDate = manufacturerDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", manufacturerDate=" + manufacturerDate +
                ", age=" + age +
                ", version=" + version +
                '}';
    }
}
