import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Customer } from './../../../models/customer.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-find-customer',
  templateUrl: './find-customer.component.html',
  styleUrls: ['./find-customer.component.css']
})
export class FindCustomerComponent implements OnInit {
  constructor(private router:Router, private titleService:Title) { }

  ngOnInit(): void {
    this.titleService.setTitle("Find-Customer");

  }
  public goToDetails(id:string):void{
    this.router.navigate(['/welcome/customer-details/' + id]) ;
}
}