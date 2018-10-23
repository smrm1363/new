package com.mohammadreza.mirali.energyconsumption.domain.common;


public enum MonthEnum {
    JAN, FEB, MAR, APR, MAY, JUN,
    JUL, AUG, SEP, OCT, NOV, DEC;
    private static MonthEnum[] vals = values();
    public MonthEnum next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }
    public MonthEnum prev()
    {
        return vals[(this.ordinal()-1) % vals.length];
    }
}
