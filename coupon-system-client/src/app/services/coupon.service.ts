import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Coupon } from '../models/coupon.model';

@Injectable({
  providedIn: 'root',
})
export class CouponService {
  constructor(private httpClient: HttpClient) {}

  public addCoupon(couponToAdd: Coupon): Observable<number> {
    let url = 'http://localhost:8080/api/company/coupon';
    let options = { headers: this.getHeaders() };
    return this.httpClient.post<number>(url, couponToAdd, options);
  }
  public getCoupon(id: number): Observable<Coupon> {
    let url = 'http://localhost:8080/api/company/coupon?couponId=' + id;
    let options = {headers : this.getHeaders()};
    return this.httpClient.get<Coupon>(url,options);
  }
  public getCouponForCustomerById(id:number): Observable<Coupon>{
    let url = 'http://localhost:8080/api/customer/get-one?couponId=' + id;
    let options = { headers: this.getHeaders() };    
    return this.httpClient.get<Coupon>(url, options);
  }
  // Company views its own coupons
  public getAllCoupons(): Observable<Coupon[]> {
    let url = 'http://localhost:8080/api/company/coupon/all';
    let options = { headers: this.getHeaders() };
    return this.httpClient.get<Coupon[]>(url, options);
  } 
  // Customer view his purchased coupons
  public getAllCustomerCoupons(): Observable<Coupon[]> {
    let url = 'http://localhost:8080/api/customer/coupon/all';
    let options = { headers: this.getHeaders() };
    return this.httpClient.get<Coupon[]>(url, options);
  } 
  // Customer viewing other company coupons
  public getAllCouponsOfCompanyId(id: number): Observable<Coupon[]> {    
    let url = 'http://localhost:8080/api/company/coupon/all-of-company/' + id;
    let options = { headers: this.getHeaders() };
    return this.httpClient.get<Coupon[]>(url, options);
  }
  public getCouponByMaxPrice(maxPrice:number):Observable<Coupon[]>{    
    let url = "http://localhost:8080/api/company/coupon/all-by-max-price?maxPrice=" + maxPrice;
    let options = {headers : this.getHeaders()}; 
    return this.httpClient.get<Coupon[]>(url,options);
  }
  public getCouponByCategory(category:string):Observable<Coupon[]>{    
    let url = "http://localhost:8080/api/company/coupon/all-by-category?category=" + category;
    let options = {headers : this.getHeaders()}; 
    return this.httpClient.get<Coupon[]>(url,options);
  }

  public getCouponByMaxPriceCustomer(maxPrice:number):Observable<Coupon[]>{  
    let url = "http://localhost:8080/api/customer/coupon/all-by-max-price?maxPrice=" + maxPrice;
    let options = {headers : this.getHeaders()}; 
    return this.httpClient.get<Coupon[]>(url,options);
  }
  public getCouponByCategoryCustomer(category:string):Observable<Coupon[]>{  
    let url = "http://localhost:8080/api/customer/coupon/all-by-category?category=" + category;
    let options = {headers : this.getHeaders()}; 
    return this.httpClient.get<Coupon[]>(url,options);
  }
  public purchaseCoupon(id: number): Observable<number>{
    let url = 'http://localhost:8080/api/customer?couponId=' + id ;
    let options = {headers : this.getHeaders()};     
    return this.httpClient.post<number>(url, null ,options);
  }

  public deleteCoupon(id: number): Observable<number> {
    let url = 'http://localhost:8080/api/company/coupon/ ' + id;
    let options = { headers: this.getHeaders() };
    return this.httpClient.delete<number>(url, options);
  }
  public updateCoupon(coupon: Coupon): Observable<number> {
    console.log(coupon);    
    let url = 'http://localhost:8080/api/company/coupon/';
    let options = { headers: this.getHeaders() };
    return this.httpClient.put<number>(url, coupon, options);
  }
  private getHeaders(): HttpHeaders {
    return new HttpHeaders().set(
      'token', sessionStorage.getItem('token') as unknown as string
    );
  }
}
