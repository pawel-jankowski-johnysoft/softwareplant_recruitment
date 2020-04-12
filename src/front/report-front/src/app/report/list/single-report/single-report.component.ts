import {Component, Input, OnInit} from '@angular/core';
import {Report} from "../../model/Report";

@Component({
  selector: 'app-single-report',
  templateUrl: './single-report.component.html',
  styleUrls: ['./single-report.component.scss']
})
export class SingleReportComponent implements OnInit {
  displayedColumns: string[] = ['film', 'planet', 'character'];

  @Input()
  public report: Report;

  @Input()
  public accordion;

  constructor() {
  }

  ngOnInit(): void {

  }

}
