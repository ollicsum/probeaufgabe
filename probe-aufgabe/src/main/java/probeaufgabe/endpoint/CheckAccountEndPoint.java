package probeaufgabe.endpoint;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import probeaufgabe.exceptions.CheckAccountNotFoundException;
import probeaufgabe.model.beans.CheckAccountInput;
import probeaufgabe.model.entities.CheckAccount;
import probeaufgabe.service.ProbeaufgabeService;

@Api(value="Checking Account End Point", description="End point for checking account management")
@RestController
public class CheckAccountEndPoint {

	@Autowired
	ProbeaufgabeService probeaufgabeService;
	Logger logger = LoggerFactory.getLogger(CheckAccountEndPoint.class);
	
	
	@ApiOperation(value = "Get all checking accounts from database", response = List.class)
	@RequestMapping(value="/probe-aufgabe/CheckAccounts", method=RequestMethod.GET)
    public @ResponseBody List<CheckAccount> getAllCheckAccounts() {
        logger.info("getAllCheckAccounts");
    	return probeaufgabeService.getAllCheckAccounts();
    }
	
	@ApiOperation(value = "Get specific checking account from database", response = List.class)
	@RequestMapping(value="/probe-aufgabe/CheckAccounts/{account_number}", method=RequestMethod.GET)
    public @ResponseBody CheckAccount getCheckAccount(@PathVariable int account_number) {
        logger.info("getCheckAccount");
        
        try {
        	return probeaufgabeService.getCheckAccount(account_number);
        } catch (CheckAccountNotFoundException e) {
        	logger.error(e.toString());
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking account " + account_number + " not found.");
        } catch (Exception e) {
        	logger.error(e.toString());
        	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@ApiOperation(value = "Create a new checking account in the database", response = String.class)
	@RequestMapping(value="/probe-aufgabe/CheckAccounts", method=RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> postUser(@Valid @RequestBody CheckAccountInput checkAccount){
		logger.info("Checking account creation");

        try {
        	CheckAccount accountCreated = probeaufgabeService.createCheckAccount(checkAccount);
			
			URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{account_number}")
                .buildAndExpand(accountCreated.getAccount_number())
                .toUri();
    		
    		return ResponseEntity.created(location).build();
    		
    	} catch (Exception e) {
    		logger.error(e.toString());
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}