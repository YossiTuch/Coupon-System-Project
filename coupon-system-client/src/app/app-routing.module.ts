import { MyCouponsComponent } from './components/coupon/my-coupons/my-coupons.component';
import { PurchaseAllCouponsComponent } from './components/coupon/purchase-all-coupons/purchase-all-coupons.component';
import { PurchaseAllCompaniesComponent } from './components/coupon/purchase-all-companies/purchase-all-companies.component';
import { AddCouponComponent } from './components/coupon/add-coupon/add-coupon.component';
import { EditCompanyComponent } from './components/company/edit-company/edit-company.component';
import { AllCompaniesComponent } from './components/company/all-companies/all-companies.component';
import { CompanyDetailsComponent } from './components/company/company-details/company-details.component';
import { EditCustomerComponent } from './components/customer/edit-customer/edit-customer.component';
import { AllCustomersComponent } from './components/customer/all-customers/all-customers.component';
import { FindCustomerComponent } from './components/customer/find-customer/find-customer.component';
import { CustomerDetailsComponent } from './components/customer/customer-details/customer-details.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/general/home/home.component';
import { NotFound404Component } from './components/general/not-found404/not-found404.component';
import { LoginComponent } from './components/general/login/login.component';
import { LayoutComponent } from './components/general/layout/layout.component';
import { AuthService } from './services/auth.service';
import { MenuComponent } from './components/general/menu/menu.component';
import { AddCompanyComponent } from './components/company/add-company/add-company.component';
import { AddCustomerComponent } from './components/customer/add-customer/add-customer.component';
import { FindCompanyComponent } from './components/company/find-company/find-company.component';
import { AllCouponsComponent } from './components/coupon/all-coupons/all-coupons.component';
import { EditCouponComponent } from './components/coupon/edit-coupon/edit-coupon.component';
import { PurchaseOneCouponComponent } from './components/coupon/purchase-one-coupon/purchase-one-coupon.component';

const routes: Routes = [
    { path: 'login', component: LoginComponent },
    {
        path: 'welcome', canActivate: [AuthService],
        component: LayoutComponent,
        children: [      
   
            { path: 'home', component: HomeComponent },
            // company pages
            { path: 'all-companies', component: AllCompaniesComponent },
            { path: 'add-company', component: AddCompanyComponent },
            { path: 'find-company', component: FindCompanyComponent },
            { path: 'company-details/:id', component: CompanyDetailsComponent },
            { path: 'edit-company/:id', component: EditCompanyComponent }, 
            // customer pages
            { path: 'all-customers', component: AllCustomersComponent },
            { path: 'add-customer', component: AddCustomerComponent },
            { path: 'find-customer', component: FindCustomerComponent },
            { path: 'customer-details/:id', component: CustomerDetailsComponent },
            { path: 'edit-customer/:id', component: EditCustomerComponent },    
            { path: 'my-coupons', component: MyCouponsComponent },    
            // coupoon pages
            { path: 'all-coupons', component: AllCouponsComponent },
            { path: 'add-coupon', component: AddCouponComponent },
            // { path: 'find-coupon', component: FindCustomerComponent },
            { path: 'edit-coupon/:id', component: EditCouponComponent },
            { path: 'purchase-all-companies', component: PurchaseAllCompaniesComponent },
            { path: 'purchase-all-coupons/:id', component: PurchaseAllCouponsComponent },    
            { path: 'purchase-one-coupon/:id', component: PurchaseOneCouponComponent },    

            

        ]
    },
    { path: '', redirectTo: "/login", pathMatch: "full" },
    { path: '**', component: NotFound404Component },
   
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
