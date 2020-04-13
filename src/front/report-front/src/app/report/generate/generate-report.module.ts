import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {GenerateReportComponent} from './generate-report/generate-report.component';
import {ImportMaterialModule} from "../../import-material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [GenerateReportComponent],
  imports: [
    CommonModule, ImportMaterialModule,
    ReactiveFormsModule, RouterModule
  ],
  exports: [GenerateReportComponent]
})
export class GenerateReportModule {
}
