/*
 * package com.code.onboarding.entity;
 * 
 * import java.io.Serializable; import java.util.ArrayList; import
 * java.util.List;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.ManyToMany; import
 * javax.persistence.SequenceGenerator; import javax.persistence.Table;
 * 
 * import com.code.onboarding.enums.RoleTypes;
 * 
 * import lombok.AllArgsConstructor; import lombok.Getter; import lombok.Setter;
 * 
 * @Entity
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @AllArgsConstructor
 * 
 * @Table(name = "T_EMP_ROLES") public class Role implements Serializable {
 * 
 * private static final long serialVersionUID = 1L;
 * 
 * @Id
 * 
 * @SequenceGenerator(sequenceName="S_EMP_ROLE", allocationSize = 1, name =
 * "S_EMP_ROLE")
 * 
 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_EMP_ROLE")
 * 
 * @Column(name = "ROLE_ID", precision = 9) private Long roleId;
 * 
 * @Column(name = "ROLE_NAME", length = 20) private RoleTypes roleType;
 * 
 * @ManyToMany(mappedBy = "roles") private List<Employee> users = new
 * ArrayList<>();
 * 
 * }
 */