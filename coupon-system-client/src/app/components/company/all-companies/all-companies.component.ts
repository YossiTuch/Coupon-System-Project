import { CompanyService } from './../../../services/company.service';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Company } from 'src/app/models/company.model';

@Component({
  selector: 'app-all-companies',
  templateUrl: './all-companies.component.html',
  styleUrls: ['./all-companies.component.css']
})
export class AllCompaniesComponent implements OnInit {

  public companies?: Company[];

  constructor(
    private titleService: Title,
    private companyService:CompanyService
  ){}

  ngOnInit(): void {
    this.titleService.setTitle('Companies');
    this.refreshCompanies();
  }
  public refreshCompanies() {
    this.companyService.getAllCompanies().subscribe(
      (coms) => {
        this.companies = coms;
      },
      (error) => {
        alert('error: ' + error.message);
      }
    );
  }
}
