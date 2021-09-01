package app.core.threads;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import app.core.entities.Coupon;
import app.core.repositories.CouponRepository;

/**
 * This class represents a Thread that checks every given time
 * for coupon that their date has expired and deletes them from the db
 */

//@Component
@Primary
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CouponExpiredChecker extends Thread{
	
	@Autowired
    private CouponRepository couponRepository;
    private boolean quit;
    public static final int DAY = 1;

    public CouponExpiredChecker(){
        quit  = false;
    }
    
    @PostConstruct
    public void startJob() {
    	run();
    }
    
    
    @Override
    public void run(){
        while(!quit){
            System.out.println("expired check for coupons is going on...");
            try { // gets the coupons list from the repository to iterate through
                for (Coupon coupon : couponRepository.findAll()){
                    if(coupon.getEndDate().isBefore(LocalDate.now())){ // checks if expired and delete if so
                        couponRepository.deleteById(coupon.getId());
                        System.out.println("Coupon with id: " + coupon.getId() + " was removed");
                    }
                }
                System.out.println("finished");
                TimeUnit.DAYS.sleep(DAY); // takes a nap for 24 hours
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
   
    @PreDestroy
    public void quit(){
        quit = true;
    }
}
