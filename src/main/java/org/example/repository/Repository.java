package org.example.repository;

import java.util.List;
import java.util.Optional;

// Repository är ett generiskt gränssnitt för datalagring och hantering.
// Det används av t.ex. BookingRepository, CustomerRepository och VehicleRepository.
public interface Repository<T, ID> {

    // Lägger till ett nytt objekt i datalagringen.
    void add(T item);

    // Hämtar ett objekt baserat på dess ID.
    // Använder Optional för att tydligt visa att resultatet kan vara null — det minskar risken för NullPointerException.
    Optional<T> findById(ID id);

    // Returnerar alla objekt som en lista.
    // List används här eftersom vi ofta vill kunna iterera över alla objekt i ordning.
    List<T> findAll();

    // Tar bort ett objekt baserat på ID.
    void remove(ID id);

    // Uppdaterar ett befintligt objekt.
    // Returnerar true/false beroende på om uppdateringen lyckades.
    boolean update(ID id, T updatedEntity);
}
