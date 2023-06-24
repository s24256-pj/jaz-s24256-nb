package kolokwium.jazs24256nbp;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class NbpService {

    private final NbpRepository nbpApiRepository;
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NbpService(NbpRepository nbpApiRepository) {
        this.nbpApiRepository = nbpApiRepository;
    }

    public Currency saveCurrency(Currency currency) {
        return nbpApiRepository.save(currency);
    }

    public double getMidValue(String name, LocalDate startDate, LocalDate endDate) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/"+name+"/"+startDate+"/"+endDate+"/";
        double midSum = 0.0;

        NbpApiResponse nbpAPi = restTemplate.getForObject(url,NbpApiResponse.class);

        int count = 0;
        assert nbpAPi != null;
        for (Rate rate : nbpAPi.getRates() ) {
            double mid = rate.getMid();
            midSum += mid;
            count++;
        }
        if (count > 0) {
            return midSum / count;
        }
        else {
            return 0.0;
        }
    }
}
