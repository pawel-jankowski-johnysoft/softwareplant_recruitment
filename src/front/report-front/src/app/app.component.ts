import {Component, OnInit} from '@angular/core';

const USERNAME = 'test';
const PASSWORD = 'test';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'report-front';

  constructor() {
  }

  ngOnInit(): void {
  }


}
