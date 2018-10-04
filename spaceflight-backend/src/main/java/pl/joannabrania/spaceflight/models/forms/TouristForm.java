package pl.joannabrania.spaceflight.models.forms;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TouristForm {

    private String name;
    private String surname;
    private String gender;
    private String country;
    private String notes;
    private LocalDate dateBirth;
}
