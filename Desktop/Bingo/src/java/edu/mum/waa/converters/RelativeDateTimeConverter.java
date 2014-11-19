/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.converters;

import java.lang.annotation.Annotation;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author mandal
 */
@FacesConverter("edu.mum.RelativeDateTimeConverter")
public class RelativeDateTimeConverter implements Converter {

    private static final String[] periods = {"second", "minute", "hour", "day", "week", "month", "year", "decade"};
    private static final double[] lengths = {60, 60, 24, 7, 4.35, 12, 10};

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        return new Date();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        Date now = new Date();
        Date date = (Date) value;
        long diff = (date.getTime() - now.getTime()) / 1000;

        int j = 0;
        for (int i = 0; diff >= lengths[i] && i < lengths.length - 1; i++) {
            j = i;
            diff = (long) (diff / lengths[i]);
        }

        String period = periods[j + 1];
        if (diff != 1) {
            period = period + "s";
        }

        return diff + " " + period + " left";
    }
}
