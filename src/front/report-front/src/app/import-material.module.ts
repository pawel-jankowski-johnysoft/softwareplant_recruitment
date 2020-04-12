import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule} from "@angular/material/button";
import {MatMenuModule} from "@angular/material/menu";
import {MatSidenavModule} from "@angular/material/sidenav";
import {NgModule} from "@angular/core";
import {MatListModule} from "@angular/material/list";
import {BrowserModule} from "@angular/platform-browser";
import {MatExpansionModule} from '@angular/material/expansion';
import {MatStepperModule} from "@angular/material/stepper";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatMenuModule,
    MatButtonModule,
    MatListModule,
    MatExpansionModule,
    MatStepperModule,
    MatFormFieldModule,
    MatInputModule
  ], exports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatMenuModule,
    MatButtonModule,
    MatListModule,
    MatExpansionModule,
    MatStepperModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class ImportMaterialModule {
}
