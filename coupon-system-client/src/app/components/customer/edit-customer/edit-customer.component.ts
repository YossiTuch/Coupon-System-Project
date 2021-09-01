import { Router, ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { CustomerService } from 'src/app/services/customer.service';
import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {

  public customer: Customer = new Customer();
  

  constructor(private activatedRoute :ActivatedRoute, private customerService:CustomerService, private titleService:Title, private router:Router) { }

  ngOnInit(): void {
    this.titleService.setTitle('Edit-Customer');
    this.getCustomer();
    }

  private getCustomer() :void{
    this.customerService.getCustomer(this.activatedRoute.snapshot.params.id).subscribe(
      (cust) => {
        this.customer =  cust;
      },
      (error)=>{
        alert(error.error.meesage);
      }
    );
  }
  public update(firstName:string, lastName:string,email:string, password:string){
    let id = this.activatedRoute.snapshot.params.id;
    this.customerService.update(new Customer(id, firstName, lastName, email, password)).subscribe(
      (number)=>{
        alert('Customer with id : ' + id + " has beed updated")
        this.router.navigate(['welcome/customer-details/' + id]);
      },
      (error)=> {
        alert('Error has occurred: ' + error.error.message);
      }
    );    
  }

}
