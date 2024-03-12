import java.time.LocalDate;

public class Zamestnanec {
    private String name;
    private String surname;
    private boolean insurance;
    private LocalDate dateOfBirth;

    public Zamestnanec(String name, String surname, boolean insurance, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.insurance = insurance;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}