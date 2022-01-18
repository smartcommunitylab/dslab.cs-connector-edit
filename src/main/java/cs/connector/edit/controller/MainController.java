package cs.connector.edit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MainController {
	
	@GetMapping("/educationalActivity")
	public ResponseEntity<List<Map<String, Object>>> getEducationalActivity(
			@RequestParam String fiscalCode,
			@RequestParam String entityType, 
			@RequestParam String viewName) {
		ObjectMapper objectMapper = new ObjectMapper();
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = null;
		try {
			resource = resourceLoader.getResource("classpath:json/educationalActivity.json");
			List<Map<String,Object>> list = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Map<String, Object>>>() {});
			return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
}
