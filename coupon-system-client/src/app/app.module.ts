import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http'
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { LayoutComponent } from './components/general/layout/layout.component';
import { HeaderComponent } from './components/general/header/header.component';
import { FooterComponent } from './components/general/footer/footer.component';
import { MenuComponent } from './components/general/menu/menu.component';
import { HomeComponent } from './components/general/home/home.component';
import { NotFound404Component } from './components/general/not-found404/not-found404.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { RootComponent } from './components/general/root/root.component';
import { LoginComponent } from './components/general/login/login.component';
import { AddCompanyComponent } from './components/company/add-company/add-company.component';
import { AddCustomerComponent } from './components/customer/add-customer/add-customer.component';
import { CustomerDetailsComponent } from './components/customer/customer-details/customer-details.component';
import { FindCustomerComponent } from './components/customer/find-customer/find-customer.component';
import { AllCustomersComponent } from './components/customer/all-customers/all-customers.component';
import { EditCustomerComponent } from './components/customer/edit-customer/edit-customer.component';
import { AllCompaniesComponent } from './components/company/all-companies/all-companies.component';
import { CompanyDetailsComponent } from './components/company/company-details/company-details.component';
import { EditCompanyComponent } from './components/company/edit-company/edit-company.component';
import { FindCompanyComponent } from './components/company/find-company/find-company.component';
import { CompanyLogoComponent } from './components/company/company-logo/company-logo.component';
import { AddCouponComponent } from './components/coupon/add-coupon/add-coupon.component';
import { AllCouponsComponent } from './components/coupon/all-coupons/all-coupons.component';
import { FindCouponComponent } from './components/coupon/find-coupon/find-coupon.component';
import { EditCouponComponent } from './components/coupon/edit-coupon/edit-coupon.component';
import { CouponDetailsComponent } from './components/coupon/coupon-details/coupon-details.component';
import { PurchaseAllCompaniesComponent } from './components/coupon/purchase-all-companies/purchase-all-companies.component';
import { PurchaseAllCouponsComponent } from './components/coupon/purchase-all-coupons/purchase-all-coupons.component';
import { MyCouponsComponent } from './components/coupon/my-coupons/my-coupons.component';
import { PurchaseOneCouponComponent } from './components/coupon/purchase-one-coupon/purchase-one-coupon.component';

@NgModule({
    declarations: [
        LayoutComponent,
        HeaderComponent,
        FooterComponent,
        MenuComponent,
        HomeComponent,        
        NotFound404Component,
        ThumbnailComponent, 
        RootComponent,
        LoginComponent,
        AddCompanyComponent,
        AddCustomerComponent,
        CustomerDetailsComponent,
        FindCustomerComponent,
        AllCustomersComponent,
        EditCustomerComponent,
        AllCompaniesComponent,
        CompanyDetailsComponent,
        EditCompanyComponent,
        FindCompanyComponent,
        CompanyLogoComponent,
        AddCouponComponent,
        AllCouponsComponent,
        FindCouponComponent,
        EditCouponComponent,
        CouponDetailsComponent,
        PurchaseAllCompaniesComponent,
        PurchaseAllCouponsComponent,
        MyCouponsComponent,
        PurchaseOneCouponComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [RootComponent]
})
export class AppModule { }
