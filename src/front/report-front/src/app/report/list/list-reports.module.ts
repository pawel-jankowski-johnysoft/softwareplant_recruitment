import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ListReportsComponent} from './list-reports/list-reports.component';
import {ImportMaterialModule} from "../../import-material.module";
import {SingleReportComponent} from "./single-report/single-report.component";
import {MatTableModule} from "@angular/material/table";


@NgModule({
  declarations: [ListReportsComponent, SingleReportComponent],
  imports: [
    CommonModule,
    ImportMaterialModule,
    MatTableModule
  ], exports: [ListReportsComponent]
})
export class ListReportsModule {
}
