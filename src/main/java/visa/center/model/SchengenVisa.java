package visa.center.model;

import java.time.LocalDate;

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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import visa.center.model.enums.NumberOfEntries;
import visa.center.model.enums.SchengenCountries;
import visa.center.model.enums.VisaCategory;
import visa.center.model.enums.VisaStatus;
import visa.center.model.enums.VisaType;

@Entity
@Table(name = "schengen_visa")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchengenVisa {

	@Id
	@Column(name = "visa_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer visaId;

	@Enumerated(EnumType.STRING)
	@Column(name = "number_of_entries")
	private NumberOfEntries entries;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private VisaType type;

	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private VisaCategory category;

	@Enumerated(EnumType.STRING)
	@Column(name = "visa_status")
	private VisaStatus visaStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "schengen_countries")
	private SchengenCountries country;

	@Column(name = "first_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate firstDate;

	@Column(name = "last_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate lastDate;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private CustomerPersonalData customerData;
}
