package app.core.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class represents the coupons.
 *
 * @author Yossi and Avi Tuchband
 * 
 */
@Entity
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	/**
	 * The Company which the coupon is owned by.
	 */
	@ManyToOne(cascade = {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
	})
	@JoinColumn(name = "company_id")
	@JsonIgnore
	private Company company;

	/**
	 * The Customer which the coupon is owned by.
	 */
	@ManyToMany
	@JoinTable(name = "customer_coupon", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	@JsonIgnore
	private List<Customer> buyersList;

	@Enumerated(EnumType.STRING)
	private Category category;
	private String title;
	private String description;

	/**
	 * The time when the Coupon was made.
	 */
	private LocalDate startDate;

	/**
	 * The Coupon's expiration date.
	 */
	private LocalDate endDate;

	private int amount;
	private double price;
	private String image;

	/**
	 * Constructs a Coupon with {@code null} in its fields.
	 */
	public Coupon() {

	}
	public Coupon(long id) {
		this.id = id;
	}
	/**
	 * Constructs a new Coupon with specified id, companyId, category, title,
	 * description, startDate, endDate, amount, price and image.
	 * 
	 * @param id          The id of The Coupon which can be retrieved by the
	 *                    {@link #getId()} method.
	 * @param category    The category of the Coupon which can be retrieved by the
	 *                    {@link #getCategory()} method.
	 * @param title       The title of the Coupon which can be retrieved by the
	 *                    {@link #getTitle()} method.
	 * @param description The description of the Coupon which can be retrieved by
	 *                    the {@link #getDescription()} method.
	 * @param startDate   The startDate of the Coupon which can be retrieved by the
	 *                    {@link #getStartDate()} method.
	 * @param endDate     The endDate of the Coupon which can be retrieved by the
	 *                    {@link #getEndDate()} method.
	 * @param amount      The amount of the Coupon uses which can be retrieved by
	 *                    the {@link #getAmount()} method.
	 * @param price       The price of the Coupon which can be retrieved by the
	 *                    {@link #getPrice()} method.
	 * @param image       The image of the Coupon which can be retrieved by the
	 *                    {@link #getImage()} method.
	 */
	public Coupon(long id, Category category, String title, String description, LocalDate startDate, LocalDate endDate,
			int amount, double price, String image) {
		this.id = id;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}
	/**
	 * @return the id of the Coupon Object.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id Sets the id of the Coupon Object.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the companyId of the Coupon Object.
	 */
	public long getCompanyId() {
		return company.getId();
	}

	/**
	 * @param company Sets the company of the Coupon Object.
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 *
	 * @return the Customers that owned this coupon
	 */
	public List<Customer> getCustomer() {
		return this.buyersList;
	}
	/**
	 * @return the category of the Coupon Object.
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category Sets the category of the Coupon Object.
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the title of the Coupon Object.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title Sets the title of the Coupon Object.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description of the Coupon Object.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description Sets the description of the Coupon Object.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startDate of the Coupon Object.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate Sets the startDate of the Coupon Object.
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate of the Coupon Object.
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate Sets the endDate of the Coupon Object.
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the amount of the Coupon Object.
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount Sets the amount of the Coupon Object.
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the price of the Coupon Object.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price Sets the price of the Coupon Object.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the image of the Coupon Object.
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image Sets the image of the Coupon Object.
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return a String representation of the Coupon Object.
	 */
	@Override
	public String toString() {
		return "Coupon{" + "id=" + id + ", company=" + company + ", category=" + category + ", title='" + title + '\''
				+ ", description='" + description + '\'' + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", price=" + price + ", image='" + image + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coupon coupon = (Coupon) o;
		return id == coupon.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
