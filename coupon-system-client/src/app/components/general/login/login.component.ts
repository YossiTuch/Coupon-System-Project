import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    public user: string = "";
    public pass: string = "";


    constructor(private router: Router, private auth: AuthService) { }

    ngOnInit(): void {
    }

    public login(clientType: string): void {
        this.auth.login(this.user, this.pass, clientType)
            .subscribe(
                (token) => {
                    sessionStorage.setItem('token', token);
                    sessionStorage.setItem('type', clientType);
                    this.router.navigate(["welcome"]);
                },
                (error) => {
                    alert("Login failed " + error.error);
                }
            );        
    }
}
