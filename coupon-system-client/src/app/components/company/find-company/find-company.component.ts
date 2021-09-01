import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-find-company',
  templateUrl: './find-company.component.html',
  styleUrls: ['./find-company.component.css']
})
export class FindCompanyComponent implements OnInit {

  constructor(private router:Router, private titleService:Title) { }

  ngOnInit(): void {
    this.titleService.setTitle("Find-Company");

  }
  public goToDetails(id:string):void{
    this.router.navigate(['/welcome/company-details/' + id]) ;
}
}