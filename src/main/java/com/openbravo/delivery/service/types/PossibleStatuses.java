package com.openbravo.delivery.service.types;

import java.util.Random;

public enum PossibleStatuses {
    R("RECEIVED"),
    P("PACKING"),
    T("TRANSIT"),
    D("DELIVERED");

    public final String textStatus; 

    PossibleStatuses(String textStatus) {
        this.textStatus = textStatus;
    }

    private static final Random randomStatus = new Random();
    
    public String getTextStatus() { return textStatus; }
   
    public String getChar() { return name();}

    public static PossibleStatuses randomStatusForThisExercise()  {
        PossibleStatuses[] statuses = values();
        return statuses[randomStatus.nextInt(statuses.length)];
    }
}
