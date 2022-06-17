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
import visa.center.model.enums.QuestionnaireStatus;

@Entity
@Table(name = "questionnaire")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Questionnaire {

	@Id
	@Column(name = "quest_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "quest_status")
	private QuestionnaireStatus status;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "visa_id")
	private SchengenVisa visa;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private CustomerPersonalData customerData;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
}
