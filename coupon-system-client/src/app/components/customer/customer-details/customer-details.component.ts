import { Subscriber } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { CustomerService } from 'src/app/services/customer.service';
import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css'],
})
export class CustomerDetailsComponent implements OnInit {
  public customer?: Customer;

  constructor(
    private customerService: CustomerService,
    private titleService: Title,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('Customer-Details');
    this.getCustomerDetails();    
  }

  public update():void{
    let id = this.activatedRoute.snapshot.params.id;
    this.router.navigate(['/welcome/edit-customer/' + id]);    
  }
  public delCustomer(): void {
    let id = this.activatedRoute.snapshot.params.id;
    this.customerService.delCustomer(id).subscribe(
      (deletedId) => {
        alert('Customer with id: ' + deletedId + ' has been deleted');
        this.router.navigate(['welcome/all-customers']);        
      },
      (error) => {
        alert(error.error.meesage);
      }
    );
  }
  public getCustomerDetails() {
    let id = this.activatedRoute.snapshot.params.id;
    this.customerService.getCustomer(id).subscribe(
      (cust) => {
        this.customer = cust;
      },
      (error) => {    
        alert(error.error.message);
      }
    );
  }
}
