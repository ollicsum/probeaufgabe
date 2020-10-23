package probeaufgabe.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import probeaufgabe.exceptions.CustomUserNumberCreationException;
import probeaufgabe.exceptions.GenderKeyNotFoundException;
import probeaufgabe.model.beans.UserInput;
import probeaufgabe.model.beans.UserResponse;
import probeaufgabe.model.dao.UserGenderRepository;
import probeaufgabe.model.entities.User;
import probeaufgabe.service.ProbeaufgabeService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class ProbeaufgabeServiceImplTest {
	@Autowired
	ProbeaufgabeService probeaufgabeService;
	@Autowired
	UserGenderRepository userGenderRepository;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@LocalServerPort
    int randomServerPort;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
	
	@Test
	public void userCreationAndUpdateTest() {
		
		//verify there is no user created yet
		assertEquals(probeaufgabeService.getAllUsers().size(), 0);
		
		//create a dummy user
		UserInput userToCreate = new UserInput("John", "Doe", LocalDate.parse("1980-01-01", formatter), 'm', "UnitTests");
		User userCreated = probeaufgabeService.createUser(userToCreate);
		
		//verify there is one user created now
		assertEquals(probeaufgabeService.getAllUsers().size(), 1);
		
		//fetch created user from DB
		UserResponse userCreatedDB = probeaufgabeService.getUser(userCreated.getUser_number());
		
		//compare created user fields to input fields;
		assertEquals(userCreatedDB.getUser_number(), userCreated.getUser_number());
		assertEquals(userCreatedDB.getFirst_name(), userToCreate.getFirst_name());
		assertEquals(userCreatedDB.getLast_name(), userToCreate.getLast_name());
		assertEquals(userCreatedDB.getBirth_date(), userToCreate.getBirth_date());
		assertEquals(userCreatedDB.getGender(), userGenderRepository.findByInsertkey(userToCreate.getGender()).getGender()); //get label of gender insert key and compare it to gender of created user
		
		//create another dummy user to update the previous one
		UserInput userToUpdate = new UserInput(userCreated.getUser_number(), "Jane", "Roe", LocalDate.parse("1985-12-31", formatter), 'f', "UnitTests");
		probeaufgabeService.putUser(userToUpdate, userCreated.getUser_number());
		
		//fetch updated user from DB
		UserResponse userUpdatedDB = probeaufgabeService.getUser(userCreated.getUser_number());
		
		//compare updated user fields to input fields;
		assertEquals(userUpdatedDB.getUser_number(), userCreated.getUser_number());
		assertEquals(userUpdatedDB.getFirst_name(), userToUpdate.getFirst_name());
		assertEquals(userUpdatedDB.getLast_name(), userToUpdate.getLast_name());
		assertEquals(userUpdatedDB.getBirth_date(), userToUpdate.getBirth_date());
		assertEquals(userUpdatedDB.getGender(), userGenderRepository.findByInsertkey(userToUpdate.getGender()).getGender());
	}
	
	@Test
	public void userCreationWithIdTest() throws URISyntaxException {
		//create a dummy user with custom ID
		UserInput userToCreate = new UserInput(1, "John", "Doe", LocalDate.parse("1980-01-01", formatter), 'm', "UnitTests");
         
        //expect HTTP status code 400
        Assert.assertEquals(HttpStatus.BAD_REQUEST, postUserInput(userToCreate));
	}
	
	@Test
	public void userCreationWithWrongGenderTest() throws URISyntaxException {
		//create a dummy user with wrong gender key
		UserInput userToCreate = new UserInput("John", "Doe", LocalDate.parse("1980-01-01", formatter), 'x', "UnitTests");
         
        //expect HTTP status code 422
        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, postUserInput(userToCreate));
	}
	
	private HttpStatus postUserInput(UserInput userInput) throws URISyntaxException {
		String baseUrl = "http://localhost:" + randomServerPort + "/probe-aufgabe/Users";
        URI uri = new URI(baseUrl);
		HttpHeaders headers = new HttpHeaders();   
        HttpEntity<UserInput> request = new HttpEntity<>(userInput, headers);
        ResponseEntity<?> result = this.restTemplate.postForEntity(uri, request, String.class);
        
        return result.getStatusCode();
	}
	
	/*
	 * Implement further unit tests...
	 */
}
