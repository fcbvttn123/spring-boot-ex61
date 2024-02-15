package ca.sheridancollege.vutran.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.vutran.beans.Appointment;
import ca.sheridancollege.vutran.repo.AppointmentRepo;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/appointments")
public class AppointmentRestController {
	private AppointmentRepo ar;
	@GetMapping
	public List<Appointment> getAllAppointments() {
		return ar.findAll();
	}
	@GetMapping(value = "{id}") 
    public Appointment getIndividualStudent(@PathVariable("id") Long id) {
        return ar.findById(id).get();
    }
	@PostMapping(consumes = "application/json")
    public Appointment postStudent(@RequestBody Appointment a) {
        return ar.save(a);
    }
	@PutMapping(consumes = "application/json")
	public String putStudentCollection(@RequestBody List<Appointment> aList) {
		ar.deleteAll();
		ar.saveAll(aList);
		return "Total Records: " + ar.count();
	}
	@PutMapping(value = "{id}", consumes = "application/json")
	public Appointment putStudent(@RequestBody Appointment a, @PathVariable("id") Long id) {
		ar.deleteById(id);
		return ar.save(a);
	}
	@DeleteMapping(value = "{id}")
	public String deleteAppointment(@PathVariable("id") Long id) {
		ar.deleteById(id);
		return "Deleted id: " + id;
	}
}
