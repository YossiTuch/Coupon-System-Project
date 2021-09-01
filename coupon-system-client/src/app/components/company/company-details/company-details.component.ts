import { CompanyService } from './../../../services/company.service';
import { Company } from './../../../models/company.model';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-company-details',
  templateUrl: './company-details.component.html',
  styleUrls: ['./company-details.component.css']
})
export class CompanyDetailsComponent implements OnInit {

  public company?: Company;

  constructor(
    private companyService: CompanyService,
    private titleService: Title,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('Company-Details');
    this.getCompanyDetails();    
  }

  public update():void{
    let id = this.activatedRoute.snapshot.params.id;
    this.router.navigate(['/welcome/edit-company/' + id]);    
  }
  public delCompany(): void {
    let id = this.activatedRoute.snapshot.params.id;
    this.companyService.delCompany(id).subscribe(
      (deletedId) => {
        alert('Company with id: ' + deletedId + ' has been deleted');
        this.router.navigate(['welcome/all-companies']);        
      },
      (error) => {
        alert(error.error.meesage);
      }
    );
  }
  public getCompanyDetails() {
    let id = this.activatedRoute.snapshot.params.id;
    this.companyService.getCompany(id).subscribe(
      (com) => {
        this.company = com;
      },
      (error) => {  
        alert(error.error.message);
      }
    );
  }
}