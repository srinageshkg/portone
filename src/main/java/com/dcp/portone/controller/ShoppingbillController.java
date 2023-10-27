package com.dcp.portone.controller;

import com.dcp.portone.entity.Shoppingbill;
import com.dcp.portone.model.response.ResponseHandler;
import com.dcp.portone.model.response.ShopRest;
import com.dcp.portone.service.ShoppingbillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/shop")
public class ShoppingbillController {
    @Autowired
    private ShoppingbillServiceImpl shoppingbillServiceImpl;

    @GetMapping("/bill")
    public ResponseEntity<Object> getShoppingbills(@RequestParam(value = "page", defaultValue = "1") int page,
              @RequestParam(value = "limit", defaultValue = "10") int limit, @RequestParam(required = false) String store){

        Set<ShopRest> shopRestSet;
        String message;
        HttpStatus httpStatus;

        System.out.println("Title " + store);

        if (store == null)
            shopRestSet = shoppingbillServiceImpl.getAllBills();
        else
            shopRestSet = shoppingbillServiceImpl.getShoppingbillByStore(store);

        if (shopRestSet.isEmpty()) {
            message = "No Bills with the details provided " + store;
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            message = "Details of Shopping bills for the Store." + store;
            httpStatus = HttpStatus.OK;
        }

        return ResponseHandler.responseMultiBuilder(message, httpStatus, shopRestSet);
    }

    @GetMapping(path = "/name/{customerName}")
    public ResponseEntity<Object> getShoppingbillByCustomerName(@PathVariable String customerName){
        List<Shoppingbill> sBills = shoppingbillServiceImpl.getShoppingbillByCustomerNameContaining(customerName);
        String message = "Shopping bill Details with Customer Name";

        message = !sBills.isEmpty() ?  "Shopping bill Details with Customer Name" : "No valid Bills Found";
        HttpStatus httpStatus = !sBills.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        //orgService.insertOrg(orgDetails);
        return ResponseHandler.responseMultiBuilder(message, httpStatus, sBills);
    }

   @GetMapping(path="/{Id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_GRAPHQL_RESPONSE_VALUE
    })
    public ResponseEntity<ShopRest> getShoppingbillById(@PathVariable Long Id){
        return new ResponseEntity<ShopRest>(shoppingbillServiceImpl.getShoppingbillById(Id), HttpStatus.OK);
    }

    @PostMapping("/bill/buy")
    public ResponseEntity<Shoppingbill> buyItems(@RequestBody ShopRest shoppingbill) {
        System.out.println(shoppingbill.toString());
        return new ResponseEntity<>(shoppingbillServiceImpl.saveShoppingBill(shoppingbill), HttpStatus.CREATED);
    }

    /*

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));
        return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());

        return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        tutorialRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        tutorialRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

     */

}
