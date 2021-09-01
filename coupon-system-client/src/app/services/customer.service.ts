import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../models/customer.model';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(private httpClient: HttpClient) {}

  public addCustomer(customerToAdd: Customer): Observable<number> {
    let url = 'http://localhost:8080/api/admin/customer/add';  
    let options = { headers: this.getHeaders()};
    return this.httpClient.post<number>(url, customerToAdd, options);
  }
  public getCustomer(id: number): Observable<Customer> {
    let options = { headers: this.getHeaders()};
    let url =
      'http://localhost:8080/api/admin/customer/get-one?customerId=' + id;
    return this.httpClient.get<Customer>(url, options);
  }
  public getAllCustomers():Observable<Customer[]>{
    let url = "http://localhost:8080/api/admin/customer/all";
    let options = { headers: this.getHeaders()};
    return this.httpClient.get<Customer[]>(url,options);
  }
  public delCustomer(id: number):Observable<number>{
    let url = "http://localhost:8080/api/admin/customer/ " + id;
    let options = {headers : this.getHeaders()};
    return this.httpClient.delete<number>(url, options);
  }
  public update(customer:Customer):Observable<number>{

    //
    let url = "http://localhost:8080/api/admin/customer/update";
    let options = {headers : this.getHeaders()};
    return this.httpClient.put<number>(url, customer, options);
  }

  private getHeaders():HttpHeaders{
    return new HttpHeaders().set('token',sessionStorage.getItem('token') as unknown as string);
  }  
  
}

