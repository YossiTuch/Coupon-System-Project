import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-company-logo',
  templateUrl: './company-logo.component.html',
  styleUrls: ['./company-logo.component.css']
})
export class CompanyLogoComponent implements OnInit {

  public images = [
    "assets/images/company/amazon.png", 
    "assets/images/company/facebook.png",
    "assets/images/company/google.png",
    "assets/images/company/netflix.png",
    "assets/images/company/twitter.png",
    "assets/images/company/youtube.png",
  ]

  public imgSrc? :string;

constructor() { }

ngOnInit(): void {    
    this.getRandomPicIndex();   
}
public getRandomPicIndex():void{
  this.imgSrc = this.images[Math.floor(Math.random()*6)];
}
}