import { Coupon } from 'src/app/models/coupon.model';
import { Router, ActivatedRoute } from '@angular/router';
import { CouponService } from 'src/app/services/coupon.service';
import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { flatten } from '@angular/compiler';

@Component({
  selector: 'app-purchase-one-coupon',
  templateUrl: './purchase-one-coupon.component.html',
  styleUrls: ['./purchase-one-coupon.component.css'],
})
export class PurchaseOneCouponComponent implements OnInit {
  public cat?: string;
  public title?: string;
  public desc?: string;
  public amount?: number;
  public price?: number;
  public start?: Date;
  public end?: Date;
  constructor(
    private titleService: Title,
    private couponService: CouponService,
    private router: Router,
    private activatedRouter: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('Purchase One Coupon');
    this.getCouponDetails();
  }
  public isExpired(): boolean {
    if (this.end != undefined) {
      let today = new Date()
      let dd = String(today.getDate()).padStart(2, '0');
      let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
      let yyyy = today.getFullYear();
      let todayString = yyyy + '-'  + mm + '-' + dd ;        
      return this.end.toString() >= todayString;
    }
    return true;
  }  
  public purchaseCoupon():void{ 
    let id = this.activatedRouter.snapshot.params.id;
    this.couponService.purchaseCoupon(id).subscribe(      
      (id) => {
        alert("Coupon with id " + id + " has been purchased");
        this.goBack();
      },
      (error) => alert("something went wrong" + error.error.message)
    )
  }
  public isInStock(){
    if(this.amount){       
      return this.amount > 0;
    }
    return false;
  }

  public getCouponDetails(): void {
    let id = this.activatedRouter.snapshot.params.id;
    this.couponService.getCouponForCustomerById(id).subscribe(
      (coup) => {
        this.cat = coup.category;
        this.title = coup.title;
        this.desc = coup.description;
        this.amount = coup.amount;
        this.price = coup.price;
        this.start = coup.startDate;
        this.end = coup.endDate;
      },
      (error) => {
        console.dir(error);

        alert(error.error.message);
      }
    );
  }
  public goBack(){
    this.router.navigate(['welcome/my-coupons']);
  }
}
