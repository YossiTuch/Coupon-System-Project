import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Coupon } from 'src/app/models/coupon.model';
import { CouponService } from 'src/app/services/coupon.service';

@Component({
  selector: 'app-add-coupon',
  templateUrl: './add-coupon.component.html',
  styleUrls: ['./add-coupon.component.css']
})
export class AddCouponComponent implements OnInit {

  public startDate?:Date;
  public endDate?:Date;
  public amount?:number;
  public price?:number;

  constructor(private couponService:CouponService, private titleService:Title, private router:Router) { }

  ngOnInit(): void {
    this.titleService.setTitle("Add-Coupon");
  }

  public addCoupon(category:string, title:string ,desc:string): void{
      let coup = new Coupon(0, category, title, desc, this.startDate, this.endDate, this.amount, this.price);               
      this.couponService.addCoupon(coup).subscribe(
        (id) =>{     
            alert("Coupon added successfully with id: " + id);
            // this.router.navigate(["coupon-details/" + id]);
        },
        (error)=>{
          alert("Something went wrong " + error.error.message);
        }
      );
  }
}
