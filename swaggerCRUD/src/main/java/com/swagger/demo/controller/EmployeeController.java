package com.swagger.demo.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.swagger.demo.model.EmployeeModel;
import com.swagger.demo.repo.EmployeeRepo;

@RestController
@CrossOrigin(value = "*")
public class EmployeeController {

	@Autowired
	EmployeeRepo empRepo;

	/*
	 * @Autowired RestTemplate restTemplate;
	 */

	@RequestMapping(value = "/getallemployees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<EmployeeModel> getAll() {
		Iterable<EmployeeModel> findAll = empRepo.findAll();
		return findAll;
	}

	@RequestMapping(value = "/getemployee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeModel getById(@PathVariable("id") Integer id) {
		Optional<EmployeeModel> findById = empRepo.findById(id.intValue());
		EmployeeModel employee = findById.get();
		return employee;
	}

	@RequestMapping(value = "/getemployees/{apikey}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getByApiKey(@PathVariable("apikey") Long apikey) {
		List<EmployeeModel> findByApikey = empRepo.findByApikey(apikey);
		return findByApikey;
	}

	@RequestMapping(value = "/createemployees", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeModel createEmployee(@RequestBody EmployeeModel emp) {
		EmployeeModel save = empRepo.save(emp);
		return save;
	}

	/*@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeModel updateEmployee(@RequestBody EmployeeModel emp, @PathVariable("id") Integer id) {
		Optional<EmployeeModel> findById = empRepo.findById(id.intValue());
		if (findById.isPresent())
			emp.setId(id);
		EmployeeModel save = empRepo.save(emp);
		return save;
	}*/

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Integer id) {
		empRepo.deleteById(id.intValue());
		return ResponseEntity.ok().build();
	}

//Create a room
	@RequestMapping(value = "/createroom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> CreateRoom(@RequestBody String json, @RequestHeader String Authorization) {
		String Url = "https://video.twilio.com/v1/Rooms";
		JSONObject jsonObj = new JSONObject(json);
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", Authorization);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		if (jsonObj.has("EnableTurn")) {
			map.add("EnableTurn", jsonObj.getString("EnableTurn"));
		}
		if (jsonObj.has("UniqueName")) {
			map.add("UniqueName", jsonObj.getString("UniqueName"));
		}
		if (jsonObj.has("MaxParticipants")) {
			map.add("MaxParticipants", jsonObj.getString("MaxParticipants"));
		}
		if (jsonObj.has("StatusCallback")) {
			map.add("StatusCallback", jsonObj.getString("StatusCallback"));
		}
		if (jsonObj.has("StatusCallbackMethod")) {
			map.add("StatusCallbackMethod", jsonObj.getString("StatusCallbackMethod"));
		}
		if (jsonObj.has("Type")) {
			map.add("Type", jsonObj.getString("Type"));
		}
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(Url, request, String.class);
		System.out.println(response.getBody());
		return response;
	}

	// Complete a Room
	@RequestMapping(value = "/completeroom/{sid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> CompleteRoom(@RequestBody String json, @PathVariable("sid") String sid,
			@RequestHeader String Authorization) {
		String Url = "https://video.twilio.com/v1/Rooms/" + sid;
		JSONObject jsonObj = new JSONObject(json);
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", Authorization);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		if (jsonObj.has("Status")) {
			map.add("Status", jsonObj.getString("Status"));
		}
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(Url, request, String.class);
		System.out.println(response.getBody());
		return response;
	}

	// Remove a Participant
	@RequestMapping(value = "/removeparticipant/{sid}/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> RemoveParticipant(@RequestBody String json, @PathVariable("sid") String sid,
			@PathVariable("name") String name, @RequestHeader String Authorization) {
		String Url = "https://video.twilio.com/v1/Rooms/" + sid + "/Participants/" + name;
		JSONObject jsonObj = new JSONObject(json);
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", Authorization);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		if (jsonObj.has("Status")) {
			map.add("Status", jsonObj.getString("Status"));
		}
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(Url, request, String.class);
		System.out.println(response.getBody());
		return response;
	}
}
