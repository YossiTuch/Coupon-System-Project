import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

    public images = [
      "assets/images/portraits/cus" + 1 + ".jpg",
      "assets/images/portraits/cus" + 2 + ".jpg",
      "assets/images/portraits/cus" + 3 + ".jpg",
      "assets/images/portraits/cus" + 4 + ".jpg",
      "assets/images/portraits/cus" + 5 + ".jpg",
      "assets/images/portraits/cus" + 6 + ".jpg",
      "assets/images/portraits/cus" + 7 + ".jpg",
    ]

    public imgSrc? :string;

  constructor() { }

  ngOnInit(): void {    
      this.getRandomPicIndex();   
  }
  public getRandomPicIndex():void{
    this.imgSrc = this.images[Math.floor(Math.random()*7)];
  }
}
