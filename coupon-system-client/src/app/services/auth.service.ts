import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class AuthService implements CanActivate {

    constructor(private router: Router, private httpClient: HttpClient) { }

    canActivate(route: import("@angular/router").ActivatedRouteSnapshot, state: import("@angular/router").RouterStateSnapshot): boolean {
        if (!this.isLoggedIn()) {
            this.router.navigate([""]);
            return false;
        }
        return true;
    }
    public isLoggedIn(): boolean {
        return sessionStorage.getItem('token') != null
    }

    public login(username: string, password: string, clientType: string): Observable<string> {
        let ct = "ct=" + clientType;
        let user = "email=" + username;
        let pass = "password=" + password;
        let url = "http://localhost:8080/login?" + ct + "&" + user + "&" + pass;
        return this.httpClient.post(url, null, { responseType: 'text' });
    }
}

