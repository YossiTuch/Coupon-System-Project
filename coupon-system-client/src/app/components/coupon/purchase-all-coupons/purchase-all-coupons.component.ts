import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Coupon } from 'src/app/models/coupon.model';
import { CouponService } from 'src/app/services/coupon.service';

@Component({
  selector: 'app-purchase-all-coupons',
  templateUrl: './purchase-all-coupons.component.html',
  styleUrls: ['./purchase-all-coupons.component.css']
})
export class PurchaseAllCouponsComponent implements OnInit {

  public coupons?: Coupon[];
  public couponId?: number;

  constructor(
    private titleService: Title,
    private couponService: CouponService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('Company Coupons');    
    this.refreshCoupons();
  }
  public refreshCoupons() {
    let id = this.activatedRoute.snapshot.params.id;      
    this.couponService.getAllCouponsOfCompanyId(id).subscribe(
      (coups) => {
        this.coupons = coups;
      },
      (error) => {  
        alert('error: ' + error.error.message);
      }
    );
  }
}
