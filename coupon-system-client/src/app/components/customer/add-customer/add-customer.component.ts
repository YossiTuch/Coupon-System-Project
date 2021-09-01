import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  constructor(private titleService:Title, private router: Router , private customerService:CustomerService) { }

  ngOnInit(): void {
    this.titleService.setTitle("Add-Customer");
  }

  public addCustomer(name: string, last : string, email: string, pass: string):void{
      let cust = new Customer(0, name, last, email, pass);
      this.customerService.addCustomer(cust).subscribe(
        (id) =>{     
            alert("Customer added successfully with id: " + id);
            this.router.navigate(["/welcome/customer-details/" + id]);
        },
        (error)=>{
          
          alert("Something went wrong " + error.error);
        }
      );
  }

}
