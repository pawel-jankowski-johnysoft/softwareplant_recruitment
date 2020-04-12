import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListReportsComponent} from "./report/list/list-reports/list-reports.component";
import {GenerateReportComponent} from "./report/generate/generate-report/generate-report.component";


const routes: Routes = [
  {path: '', component: ListReportsComponent},
  {path: 'report/list', component: ListReportsComponent},
  {path: 'report/add', component: GenerateReportComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
