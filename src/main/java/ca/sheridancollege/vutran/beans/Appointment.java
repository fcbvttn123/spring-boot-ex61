package ca.sheridancollege.vutran.beans;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String purpose;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate appointmentDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime appointmentTime;
}



/*
 
{
  "purpose": "Meeting with client",
  "appointmentDate": "2024-02-07",
  "appointmentTime": "09:00"
}
	
*/
