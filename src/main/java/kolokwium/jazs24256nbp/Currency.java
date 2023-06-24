package kolokwium.jazs24256nbp;

//import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Indywidualny identyfikator waluty", type="Long",minimum = "1")
    private Long id;

    @Schema(description = "Skrócona nazwa waluty - kod tej waluty",type = "String",minimum = "3" ,maximum = "3")
    private String name;

    @Schema(description = "Obliczony średni kurs waluty z wybranych ilości dni",type = "double")
    private double averagerate;
    @Schema(description = "Data, od której chcemy zacząć przedział", type="LocalDate", maximum = "Dzisiejsza data", minimum ="2002-01-02")
    private LocalDate startDate;
    @Schema(description = "Data, na której chcemy zakończyć przedział", type="LocalDate", maximum = "Dzisiejsza data", minimum ="2002-01-02")
    private LocalDate endDate;

    @Schema(description = "Czas, w którym zostało wysłane zapytanie", type="LocalDateTime")
    private LocalDateTime time;

    public Currency(){

    }
    public Currency(Long id,int days,String name,double averagerate,LocalDateTime time,LocalDate startDate,LocalDate endDate){
        this.averagerate = averagerate;
        this.id = id;
        this.name = name;
        this.time = time;
        this.endDate = endDate;
        this.startDate = startDate;


    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public double getAveragerate() {
        return averagerate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setAveragerate(double averagerate) {
        this.averagerate = averagerate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

