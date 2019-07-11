package pojos;

import java.util.Date;
import javax.persistence.*;

@Entity //mandatory ---class level ---run time anno
@Table(name="iacsd_custs")
public class Customer {
	private Integer id;
	private String name,email,password;
	private double regAmount;
	private Date regDate;
	private String role;
	private CustomerType custType;
	//image
	private byte[] pic;
	public Customer() {
		System.out.println("in cust constr...");
	}
	
	public Customer(String name, String email, String password, double regAmount, Date regDate, String role,
			CustomerType custType) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.regAmount = regAmount;
		this.regDate = regDate;
		this.role = role;
		this.custType = custType;
	}

	//all getters n setters
	@Id //mandatory
	//for oracle --strategy=GenerationType.AUTO
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=30,unique=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(length=30)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="reg_amt")
	public double getRegAmount() {
		return regAmount;
	}
	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}
	@Column(name="reg_date")
	@Temporal(TemporalType.DATE)
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Column(length=20)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="cust_type")
	public CustomerType getCustType() {
		return custType;
	}
	public void setCustType(CustomerType custType) {
		this.custType = custType;
	}
	@Lob
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", regAmount="
				+ regAmount + ", regDate=" + regDate + ", role=" + role + ", custType=" + custType + "]";
	}
	
	

}




