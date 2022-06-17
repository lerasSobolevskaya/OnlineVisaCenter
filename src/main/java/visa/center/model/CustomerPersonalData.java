package visa.center.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import visa.center.model.enums.Gender;

@Entity
@Table(name = "customer_personal_data")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPersonalData {

	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "patronymic")
	private String patronymic;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	@Column(name = "address")
	private String address;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "passport_id")
	private Passport passport;
}
