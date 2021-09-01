import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Coupon } from 'src/app/models/coupon.model';
import { CouponService } from 'src/app/services/coupon.service';

@Component({
  selector: 'app-my-coupons',
  templateUrl: './my-coupons.component.html',
  styleUrls: ['./my-coupons.component.css']
})
export class MyCouponsComponent implements OnInit {

  public coupons?: Coupon[];
  public maxPrice?:number;
  public category?:string;

  public isMaxPriced = false;
  public isCategorised = false;

  constructor(
    private titleService: Title,
    private couponService: CouponService
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('My Coupons');
    this.refreshCoupons();  
    
  }
  public getCouponByMaxPrice() {
    if(this.isMaxPriced){
      this.isMaxPriced = false;
      this.refreshCoupons();
      return;
    }  

    if(!this.maxPrice){
      this.maxPrice = 0 ;
    }
    this.couponService
      .getCouponByMaxPriceCustomer(this.maxPrice)
      .subscribe((coups: Coupon[]) => {
        this.coupons = coups;
        this.isMaxPriced = true;
        this.isCategorised = false;
        
      },
      (error) => {
        alert(error.error.message);
      }
      
      );
  }
  public getCouponByCategory() {    
    if(this.isCategorised){
      this.isCategorised = false;
      this.refreshCoupons();
      return;
    }
    if (!this.category){
      this.category = " ";
    }
    this.couponService
      .getCouponByCategoryCustomer(this.category)
      .subscribe((coups: Coupon[]) => {
        this.coupons = coups; 
        
        this.isCategorised = true;
        this.isMaxPriced = false;     
      },
      (error) => {
        
        alert(error.error.message);
      }
      
      );
  }
  public refreshCoupons() {
    this.couponService.getAllCustomerCoupons().subscribe(
      (coups) => {
        this.coupons = coups;
      },
      (error) => {
        alert('error: ' + error.error.message);
      }
    );
  }
}
