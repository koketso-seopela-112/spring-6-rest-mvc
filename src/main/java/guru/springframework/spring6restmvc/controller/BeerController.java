package guru.springframework.spring6restmvc.controller;
import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;


    @PatchMapping("/{beerId}")
    public ResponseEntity<Beer> updateBeerPatchById(@PathVariable UUID beerId,@RequestBody Beer beer){

        beerService.updateBeerPatchById(beerId,beer);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{beerId}")
    public ResponseEntity<Beer> deleteBeerById(@PathVariable UUID beerId){

        beerService.deleteByID(beerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Beer> updateById(@PathVariable UUID beerId,@RequestBody Beer beer){

        beerService.updateBeerById(beerId,beer);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @RequestMapping
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @PostMapping
    public ResponseEntity<Beer> handlePost(@RequestBody Beer beer){
        Beer savedBeer = beerService.saveBeer(beer);

        HttpHeaders headers  = new HttpHeaders();
        headers.add("Location","/api/v1/beer" + savedBeer.getId().toString());
        return  new ResponseEntity<>(headers,HttpStatus.CREATED);
    }
    @RequestMapping(value = "/{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerid){

        log.debug("Get Beer by Id - in controller -123");

        return beerService.getBeerById(beerid);
    }

}