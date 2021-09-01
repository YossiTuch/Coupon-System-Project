import { CustomerService } from 'src/app/services/customer.service';
import { Customer } from '../../../models/customer.model';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-all-customers',
  templateUrl: './all-customers.component.html',
  styleUrls: ['./all-customers.component.css'],
})
export class AllCustomersComponent implements OnInit {
  public customers?: Customer[];

  constructor(
    private titleService: Title,
    private customerService: CustomerService
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('Customers');
    this.refreshCustomers();
  }
  public refreshCustomers() {
    this.customerService.getAllCustomers().subscribe(
      (custs) => {
        this.customers = custs;
      },
      (error) => {
        alert('error: ' + error.message);
      }
    );
  }
}
