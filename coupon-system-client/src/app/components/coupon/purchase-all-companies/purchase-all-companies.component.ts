import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Company } from 'src/app/models/company.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-purchase-all-companies',
  templateUrl: './purchase-all-companies.component.html',
  styleUrls: ['./purchase-all-companies.component.css']
})
export class PurchaseAllCompaniesComponent implements OnInit {

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
