import {Component, OnInit} from '@angular/core';
import {ReportsProvider} from "./reports-provider";
import {Observable} from "rxjs";
import {Report} from "../../model/Report";

@Component({
  selector: 'app-list-reports-component',
  templateUrl: './list-reports.component.html',
  styleUrls: ['./list-reports.component.scss']
})
export class ListReportsComponent implements OnInit {

  reports: Observable<Report[]>;

  constructor(private reportsProvider: ReportsProvider) {
  }


  ngOnInit(): void {
    this.reports = this.reportsProvider.getAllReports();
  }
}
