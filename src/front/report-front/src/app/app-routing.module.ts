import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListReportsComponent} from "./report/list/list-reports/list-reports.component";
import {GenerateReportComponent} from "./report/generate/generate-report/generate-report.component";
import {AuthGuard} from "./auth.guard";
import {LoginComponent} from "./auth/login/login.component";


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {
    path: 'report', canActivate: [AuthGuard], children: [
      {path: 'list', component: ListReportsComponent},
      {path: 'add', component: GenerateReportComponent}]
  },
  {path: '**', redirectTo: 'report/list', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
