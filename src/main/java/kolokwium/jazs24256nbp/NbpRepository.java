package kolokwium.jazs24256nbp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NbpRepository extends JpaRepository<Currency, Long> {
    @Override
    Currency save(Currency currency);
}