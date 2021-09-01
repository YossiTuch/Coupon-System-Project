import { Injectable } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Company } from '../models/company.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  constructor(private httpClient: HttpClient) {}

  public addCompany(companyToAdd: Company): Observable<number> {
    let url = 'http://localhost:8080/api/admin/company/add';
    let options = { headers: this.getHeaders()};
    return this.httpClient.post<number>(url, companyToAdd, options);
  }
  public getCompany(id: number): Observable<Company> {
    let options = { headers: this.getHeaders() };
    let url = 'http://localhost:8080/api/admin/company/get-one?companyId=' + id;
    return this.httpClient.get<Company>(url, options);
  }
  public getAllCompanies(): Observable<Company[]> {
    let url = 'http://localhost:8080/api/admin/company/all';
    let options = { headers: this.getHeaders() };
    return this.httpClient.get<Company[]>(url, options);
  }
  public delCompany(id: number): Observable<number> {
    let url = 'http://localhost:8080/api/admin/company/ ' + id;
    let options = { headers: this.getHeaders() };
    return this.httpClient.delete<number>(url, options);
  }
  public update(company: Company): Observable<number> {
    let url = 'http://localhost:8080/api/admin/company/update';
    let options = { headers: this.getHeaders() };
    return this.httpClient.put<number>(url, company, options);
  }

  private getHeaders(): HttpHeaders {
    return new HttpHeaders().set(
      'token',
      sessionStorage.getItem('token') as unknown as string
    );
  }
}
