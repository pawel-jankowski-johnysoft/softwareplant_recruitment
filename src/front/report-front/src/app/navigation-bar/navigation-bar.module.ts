import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavigationBarComponent} from "./navigation-bar.component";
import {ImportMaterialModule} from "../import-material.module";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [NavigationBarComponent],
  imports: [
    CommonModule, ImportMaterialModule, RouterModule
  ], exports: [NavigationBarComponent]
})
export class NavigationBarModule {
}
