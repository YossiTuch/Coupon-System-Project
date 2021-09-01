import { CompanyService } from './../../../services/company.service';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from 'src/app/models/company.model';

@Component({
  selector: 'app-edit-company',
  templateUrl: './edit-company.component.html',
  styleUrls: ['./edit-company.component.css']
})
export class EditCompanyComponent implements OnInit {

  public company: Company = new Company();
  

  constructor(private activatedRoute :ActivatedRoute, private companyService:CompanyService, private titleService:Title, private router:Router) { }

  ngOnInit(): void {
    this.titleService.setTitle('Edit-Customer');
    this.getCustomer();
    }

  private getCustomer() :void{
    this.companyService.getCompany(this.activatedRoute.snapshot.params.id).subscribe(
      (comp) => {
        this.company =  comp;
      },
      (error)=>{
        alert(error.error.meesage);
      }
    );
  }
  public update(email:string, password:string){
    let id = this.activatedRoute.snapshot.params.id;
    this.companyService.update(new Company(id, "" , email, password)).subscribe(
      (number)=>{
        alert('Company with id : ' + id + " has beed updated")
        this.router.navigate(['welcome/company-details/' + id]);
      },
      (error)=> {
        alert('Error has occurred: ' + error.error.message);
      }
    );    
  }

}
