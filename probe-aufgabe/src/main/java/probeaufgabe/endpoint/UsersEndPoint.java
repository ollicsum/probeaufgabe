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
import probeaufgabe.exceptions.CustomUserNumberCreationException;
import probeaufgabe.exceptions.DifferentUriAndBodyIdsException;
import probeaufgabe.exceptions.GenderKeyNotFoundException;
import probeaufgabe.exceptions.UserNotFoundException;
import probeaufgabe.model.beans.UserInput;
import probeaufgabe.model.beans.UserResponse;
import probeaufgabe.model.entities.User;
import probeaufgabe.service.ProbeaufgabeService;

@Api(value="Users End Point", description="End point for users management")
@RestController
public class UsersEndPoint {

	@Autowired
	ProbeaufgabeService probeaufgabeService;
	Logger logger = LoggerFactory.getLogger(UsersEndPoint.class);
	
	
	@ApiOperation(value = "Get all users from database", response = List.class)
	@RequestMapping(value="/probe-aufgabe/Users", method=RequestMethod.GET)
    public @ResponseBody List<UserResponse> getAllUsers(){
        logger.info("getAllUsers");
    	return probeaufgabeService.getAllUsers();
    }
	
	@ApiOperation(value = "Get a specific user from database", response = List.class)
	@RequestMapping(value="/probe-aufgabe/Users/{user_number}", method=RequestMethod.GET)
    public @ResponseBody UserResponse getUser(@PathVariable int user_number){
        logger.info("getUser " + user_number);
        UserResponse user;
        
        try {
        	user = probeaufgabeService.getUser(user_number);
        } catch (UserNotFoundException e) {
        	logger.error(e.toString());
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + user_number + " not found.");
        } catch (Exception e) {
        	logger.error(e.toString());
        	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return user;
    }
	
	@ApiOperation(value = "Create a new user in the database", response = String.class)
	@RequestMapping(value="/probe-aufgabe/Users", method=RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> postUser(@Valid @RequestBody UserInput userInput){
		logger.info("User creation");

        try {
        	User userCreated = probeaufgabeService.createUser(userInput);
			
			URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{user_number}")
                .buildAndExpand(userCreated.getUser_number())
                .toUri();
    		
    		return ResponseEntity.created(location).build();
    		
    	} catch (CustomUserNumberCreationException e) {
    		logger.error(e.toString());
    		return new ResponseEntity<>("User creation with custom number not allowed.", HttpStatus.BAD_REQUEST);
    	} catch (GenderKeyNotFoundException e) {
    		logger.error(e.toString());
    		return new ResponseEntity<>("Gender key '" + userInput.getGender() + "' not recognized. Possible values: " + probeaufgabeService.getAvailableGenderKeys() + ".", HttpStatus.UNPROCESSABLE_ENTITY);
    	} catch (Exception e) {
    		logger.error(e.toString());
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
	
	@ApiOperation(value = "Update a user in the database", response = String.class)
	@RequestMapping(value="/probe-aufgabe/Users/{user_number}", method=RequestMethod.PUT)
    public @ResponseBody ResponseEntity<?> putUser(@Valid @RequestBody UserInput userInput, @PathVariable int user_number){
        logger.info("putUser");

        try {
        	probeaufgabeService.putUser(userInput, user_number);
        	return new ResponseEntity<>("User number '" + user_number + "' successfully updated.", HttpStatus.OK);
        	
    	} catch (CustomUserNumberCreationException e) {
    		logger.error(e.toString());
    		return new ResponseEntity<>("User number creation not allowed.", HttpStatus.BAD_REQUEST);
    		
    	} catch (DifferentUriAndBodyIdsException e) {
    		logger.error(e.toString());
    		return new ResponseEntity<>("URI and body IDs don't match.", HttpStatus.CONFLICT);
    		
    	} catch (GenderKeyNotFoundException e) {
    		logger.error(e.toString());
    		return new ResponseEntity<>("Gender key '" + userInput.getGender() + "' not recognized. Possible values: " + probeaufgabeService.getAvailableGenderKeys() + ".", HttpStatus.UNPROCESSABLE_ENTITY);
    		
    	} catch (Exception e) {
    		logger.error(e.toString());
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}