import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

    constructor(private router: Router, private authservice: AuthService) { }
    ngOnInit(): void {
        this.getClientType();
    }
    public logout(): void {
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('type');
        this.router.navigate([""]);

    }
    public getClientType(): string {
        return sessionStorage.getItem('type') as unknown as string;
    }
}