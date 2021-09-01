import { CouponService } from 'src/app/services/coupon.service';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';

@Component({
  selector: 'app-edit-coupon',
  templateUrl: './edit-coupon.component.html',
  styleUrls: ['./edit-coupon.component.css'],
})
export class EditCouponComponent implements OnInit {
  public category?: string;
  public title?: string;
  public description?: string;
  public startDate?: Date;
  public endDate?: Date;
  public amount?: number;
  public price?: number;

  constructor(
    private activatedRoute: ActivatedRoute,
    private titleService: Title,
    private couponService: CouponService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('Edit-Coupon');
    this.getCouponDetails();
  }

  public getCouponDetails(): void {
    let id = this.activatedRoute.snapshot.params.id;
    this.couponService.getCoupon(id).subscribe(
      (coupon) => {
        this.category = coupon.category;
        this.title = coupon.title;
        this.description = coupon.description;
        this.startDate = coupon.startDate;
        this.endDate = coupon.endDate;
        this.amount = coupon.amount;
        this.price = coupon.price;
      },
      (error) => {
        alert(error.error.message);
      }
    );
  }
  public updateCoupon(): void {
    let id = this.activatedRoute.snapshot.params.id;
    let coupon = new Coupon(
      id,
      this.category,
      this.title,
      this.description,
      this.startDate,
      this.endDate,
      this.amount,
      this.price
    );
    this.couponService.updateCoupon(coupon).subscribe(
      (updatedId) => {
        alert('Coupon with id: ' + updatedId + ' has been updated');
        this.router.navigate(['/welcome/all-coupons']);
      },
      (error) => {
        alert(error.error.message);
      }
    );
  }
  public deleteCoupon() {
    this.couponService
      .deleteCoupon(this.activatedRoute.snapshot.params.id)
      .subscribe(
        (deletedId) => {
          alert('Coupon with id: ' + deletedId + ' has been deleted');
          this.router.navigate(['/welcome/all-coupons']);
        },
        (error) => {
          alert(error.error.message);
        }
      );
  }
}
