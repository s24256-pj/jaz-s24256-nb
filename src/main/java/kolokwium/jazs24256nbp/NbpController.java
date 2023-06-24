package kolokwium.jazs24256nbp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/nbp")
public class NbpController {

    private final NbpService nbpService;
    private RestTemplate restTemplate;

    @Autowired
    public NbpController(NbpService nbpService, RestTemplate restTemplate) {
        this.nbpService = nbpService;
        this.restTemplate = restTemplate;
        this.nbpService.setRestTemplate(this.restTemplate);
    }

    @Operation(summary = "Oblicz średni kurs wybranej waluty od wybranej daty startu do wybranej daty końcowej")
    @ApiResponse(responseCode = "200", description = "Średni kurs waluty został obliczony i zapytanie zostało zapisane do bazy danych")
    @ApiResponse(responseCode = "400", description = "Invalid",content = @Content)
    @ApiResponse(responseCode = "404", description = "Currency not found",content = @Content )
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    @GetMapping("/kurs/{name}")
    ResponseEntity<Double> averageRate(@PathVariable String name, @RequestParam LocalDate startdate, LocalDate enddate){
        LocalDateTime day = LocalDateTime.now();
        double average = nbpService.getMidValue(name,startdate,enddate);
        Currency currency = new Currency();
        currency.setTime(day);
        currency.setName(name);
        currency.setAveragerate(average);
        currency.setEndDate(enddate);
        currency.setStartDate(startdate);
        nbpService.saveCurrency(currency);

        return ResponseEntity.ok(average);
    }
}
