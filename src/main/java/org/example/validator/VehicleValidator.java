
package org.example.validator;


import org.example.exceptions.InvalidRegistrationNumberException;
import org.example.exceptions.InvalidYearModelException;
import org.example.models.Vehicle;


public class VehicleValidator {


    public void validateRegistrationNumber(String reg){
        if(!reg.matches("^[A-ZÅÄÖ]{3}[0-9]{2}[A-Z0-9ÅÄÖ]$")) {
            throw new InvalidRegistrationNumberException("Ogiltigt registreringsnummer " + reg);
        }
    }
    public void validateModel(String model){
        if (model == null || model.isBlank()){
            throw new IllegalArgumentException("Bil modell måste anges får inte vara null ");
        }
    }
    public void validateYearModel(int year){
        if(year < 1900 || year >=2026 ){
            throw new InvalidYearModelException("Årsmodell kan inte vara under år 1900 eller över 2026 " + year);
        }
    }


}
