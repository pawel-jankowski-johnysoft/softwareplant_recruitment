import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavigationBarModule} from "./navigation-bar/navigation-bar.module";
import {ImportMaterialModule} from "./import-material.module";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ListReportsModule} from "./report/list/list-reports.module";
import {GenerateReportModule} from "./report/generate/generate-report.module";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {CommonModule} from "@angular/common";
import {AuthModule} from "./auth/auth.module";
import {httpInterceptorProviders} from "./http-interceptors";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CommonModule,
    AppRoutingModule,
    ImportMaterialModule,
    NavigationBarModule,
    ListReportsModule,
    GenerateReportModule,
    HttpClientModule,
    AuthModule
  ],
  providers: [HttpClient, httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
