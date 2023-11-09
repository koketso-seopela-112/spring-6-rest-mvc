package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j //Logging using Lombok
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, Beer> beerMap;


    public  BeerServiceImpl(){
        this.beerMap = new HashMap<>();


        log.debug("Get Beer id in service called");
        Beer beer1 = Beer.builder().id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.LAGER)
                .upc("12345")
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .quantityOnHand(122)
                .price(new BigDecimal("12.99"))
                .build();
        Beer beer2 = Beer.builder().id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123454321")
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .quantityOnHand(112)
                .price(new BigDecimal("13.99"))
                .build();

        Beer beer3 = Beer.builder().id(UUID.randomUUID())
                .version(1)
                .beerName("Castle")
                .beerStyle(BeerStyle.STOUT)
                .upc("12345")
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .quantityOnHand(125)
                .price(new BigDecimal("15.99"))
                .build();

        beerMap.put(beer1.getId(),beer1);
        beerMap.put(beer2.getId(),beer2);
        beerMap.put(beer3.getId(),beer3);

    }
    @Override
    public Beer getBeerById(UUID id) {
        return beerMap.get(id);
    }

    @Override
    public List<Beer> listBeers(){
        return  new ArrayList<>(beerMap.values());
    }
}
