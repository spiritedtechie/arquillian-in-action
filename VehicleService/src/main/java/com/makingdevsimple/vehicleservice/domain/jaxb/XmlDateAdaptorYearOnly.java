package com.makingdevsimple.vehicleservice.domain.jaxb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XmlDateAdaptorYearOnly extends XmlAdapter<String, Date> {

    private static final String DATE_FORMAT = "yyyy";

    @Override
    public String marshal(final Date date) throws Exception {
        final DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(date);
    }

    @Override
    public Date unmarshal(final String date) throws Exception {
        final DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.parse(date);
    }

}
