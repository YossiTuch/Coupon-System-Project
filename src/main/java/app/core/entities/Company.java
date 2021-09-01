package app.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * This class represents the company Objects.
 * 
 * @author Yossi and Avi Tuchband
 *
 */
@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	@JsonIgnore
	private List<Coupon> coupons;

	/**
	 * Constructs a new Company Object with {@code null} in its fields.
	 */
	public Company() {

	}

	/**
	 * constructs a new Company Object with specified id, name, email and password.
	 * 
	 * @param id       The number of the Company (which can be retrieved by the
	 *                 {@link #getId()} method).
	 * @param name     The name of the Company (which can be retrieved by the
	 *                 {@link #getName()} method).
	 * @param email    The email of the Company (which can be retrieved by the
	 *                 {@link #getEmail()} method).
	 * @param password The password of the Company (which can be retrieved by the
	 *                 {@link #getPassword()} method).
	 */
	public Company(long id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * constructs a new Company Object with specified id, email and password
	 * 
	 * @param id       The number of the Company (which can be retrieved by the
	 *                 {@link #getId()} method).
	 * @param email    The email of the Company (which can be retrieved by the
	 *                 {@link #getEmail()} method).
	 * @param password The password of the Company (which can be retrieved by the
	 *                 {@link #getPassword()} method).
	 */
	public Company(long id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	/**
	 * Constructs a new Company Object with specified name, email and password.
	 * 
	 * 
	 * @param name     The name of the Company (which can be retrieved by the
	 *                 {@link #getName()} method).
	 * @param email    The email of the Company (which can be retrieved by the
	 *                 {@link #getEmail()} method).
	 * @param password The password of the Company (which can be retrieved by the
	 *                 {@link #getPassword()} method).
	 */
	public Company(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Company(long id, String email, String password, List<Coupon> list) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = null; // for updating measures
		List<Coupon> coupons = new ArrayList<Coupon>();
	}

	/**
	 * @return the id of the Company Object
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id Sets the id of the Company Object
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name of the Company Object
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Sets the name of the Company Object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email of the Company Object
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email Sets the email of the Company Object
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password of the Company Object
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password Sets the password of the Company Object
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the Coupon List of the the Company Object
	 */
	public List<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * initialize the coupon list if its null then add a coupon to the list
	 * 
	 * @param coup - the coupon to add
	 */
	public void addCoupon(Coupon coup) {
		if (coupons == null) {
			coupons = new ArrayList<>();
		}
		coup.setCompany(this);
		coupons.add(coup);
	}

	public void deleteCoupon(Coupon coup) {
		if (coupons == null || coupons.size() == 0) {
			return;
		}
		coupons.remove(coup);
	}

	/**
	 * @param coupons Sets the coupons of the Company Object
	 */
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	/**
	 * @return a String representation of the Company Object
	 */
	@Override
	public String toString() {
		return "Company{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", password='"
				+ password + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Company company = (Company) o;
		return id == company.id && Objects.equals(name, company.name) && Objects.equals(email, company.email)
				&& Objects.equals(password, company.password) && Objects.equals(coupons, company.coupons);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}