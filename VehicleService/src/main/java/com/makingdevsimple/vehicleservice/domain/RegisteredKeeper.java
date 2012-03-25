package com.makingdevsimple.vehicleservice.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.makingdevsimple.vehicleservice.domain.jaxb.XmlDateAdaptorYearMonthDay;

@Entity
@XmlRootElement
public class RegisteredKeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement
    private String firstName;

    @XmlElement
    private String lastName;

    @XmlElement
    private String houseNumber;

    @XmlElement
    private String street;

    @XmlElement
    private String area;

    @XmlElement
    private String city;

    @XmlElement
    private String postcode;

    @XmlElement
    @XmlJavaTypeAdapter(value = XmlDateAdaptorYearMonthDay.class)
    private Date registeredFrom;

    @XmlElement
    @XmlJavaTypeAdapter(value = XmlDateAdaptorYearMonthDay.class)
    private Date registeredTo;

    public RegisteredKeeper() {
    }

    public RegisteredKeeper(final String firstName, final String lastName, final String houseNumber,
            final String street, final String area, final String city, final String postcode,
            final Date registeredFrom, final Date registeredTo) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.houseNumber = houseNumber;
        this.street = street;
        this.area = area;
        this.city = city;
        this.postcode = postcode;
        this.registeredFrom = registeredFrom;
        this.registeredTo = registeredTo;
    }

}
