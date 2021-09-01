import { CompanyService } from '../../../services/company.service';
import { Company } from '../../../models/company.model';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css']
})
export class AddCompanyComponent implements OnInit {

  constructor(private companyService:CompanyService, private titleService:Title, private router:Router) { }

  ngOnInit(): void {
    this.titleService.setTitle("Add-Company");
  }

  public addCompany(name:string, email:string ,password:string): void{
      let com = new Company(0,name,email,password);               
      this.companyService.addCompany(com).subscribe(
        (id) =>{     
            alert("Company added successfully with id: " + id);
            this.router.navigate(["welcome/company-details/" + id]);
        },
        (error)=>{       
          alert("Something went wrong: " + error.error.message);
        }
      );
  }
}

