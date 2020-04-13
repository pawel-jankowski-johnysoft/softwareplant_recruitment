import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {flatMap} from "rxjs/operators";

const USERNAME = 'test';
const PASSWORD = 'test';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'report-front';

  constructor(private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    const formData = new FormData();
    formData.append('username', USERNAME);
    formData.append('password', PASSWORD);

    const subscription = this.http.post('/login', formData)
      .pipe(flatMap(_ => this.router.navigate(['/report/list'])))
      .subscribe(_ => subscription.unsubscribe());
  }


}
