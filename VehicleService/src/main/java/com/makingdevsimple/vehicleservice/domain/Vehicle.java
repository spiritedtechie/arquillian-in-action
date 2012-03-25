package com.makingdevsimple.vehicleservice.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.makingdevsimple.vehicleservice.domain.jaxb.XmlDateAdaptorYearMonthDay;
import com.makingdevsimple.vehicleservice.domain.jaxb.XmlDateAdaptorYearOnly;

@Entity
@XmlRootElement
public class Vehicle {

    @Id
    @XmlElement
    private String registrationNumber;

    @XmlElement
    private String make;

    @XmlElement
    private String model;

    @XmlElement
    private String engineSize;

    @XmlElement
    @XmlJavaTypeAdapter(value = XmlDateAdaptorYearOnly.class)
    private Date yearOfManufacture;

    @OneToOne(cascade = { CascadeType.ALL })
    @XmlElement
    private RegisteredKeeper registeredKeeper;

    @XmlElement
    @XmlJavaTypeAdapter(value = XmlDateAdaptorYearMonthDay.class)
    private Date motDueDate;

    public Vehicle() {
    }

    public Vehicle(final String registrationNumber, final String make, final String model, final String engineSize,
            final Date yearOfManufacture, final RegisteredKeeper registeredKeeper, final Date motDueDate) {

        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        this.engineSize = engineSize;
        this.yearOfManufacture = yearOfManufacture;
        this.registeredKeeper = registeredKeeper;
        this.motDueDate = motDueDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

}
