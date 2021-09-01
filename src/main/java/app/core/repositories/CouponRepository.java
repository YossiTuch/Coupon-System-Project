package app.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.core.entities.Category;
import app.core.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	@Query("from Coupon where company.id=:companyId")
	List<Coupon> findByCompanyId(long companyId);

	List<Coupon> findByBuyersListId(long id);

	Optional<Coupon> findByIdAndBuyersListId(long couponId, long buyerId);

	List<Coupon> findByBuyersListIdAndCategory(long buyerId, Category category);

	List<Coupon> findByBuyersListIdAndPriceLessThanEqual(long buyerId, double price);

	@Query("from Coupon where company.id=:companyId AND category=:category")
	List<Coupon> findByCompanyIdAndCategory(long companyId, Category category);

	@Query("from Coupon where company.id=:companyId AND price<=:price")
	List<Coupon> findByCompanyIdAndPriceLessThanEqual(long companyId, double price);

	@Query("from Coupon where company.id=:companyId AND title=:title")
	Optional<Coupon> findByCompanyIdAndTitle(long companyId, String title);
	
	@Query("from Coupon where id=:couponId And company.id=:companyId")
	Optional<Coupon> findByIdAndCompanyId(long couponId, long companyId);

	@Query("from Coupon where id<>:couponId AND company.id=:companyId AND title=:title")
	Optional<Coupon> findByIdIsNotAndCompanyIdAndTitle(long couponId, long companyId, String title);
}
