import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReportMainComponent} from './report-main/report-main.component';
import {ImportMaterialModule} from "../import-material.module";
import {RouterModule} from "@angular/router";
import {NavigationBarModule} from "../navigation-bar/navigation-bar.module";


@NgModule({
  declarations: [ReportMainComponent],
  imports: [
    CommonModule, ImportMaterialModule, RouterModule, NavigationBarModule
  ]
})
export class ReportModule {
}
