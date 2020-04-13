import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ReportGeneratingCriteria, ReportGenerator} from "./ReportGenerator";
import {catchError, switchMap} from "rxjs/operators";
import {HttpErrorResponse} from "@angular/common/http";
import {from, throwError} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-generate-navigation',
  templateUrl: './generate-report.component.html',
  styleUrls: ['./generate-report.component.scss']
})
export class GenerateReportComponent implements OnInit {
  reportCriteria: FormGroup;
  reportId: FormControl = new FormControl('', [Validators.required, Validators.min(1)]);
  characterName: FormControl = new FormControl('', [Validators.minLength(3), Validators.required])
  planetName: FormControl = new FormControl('', [Validators.minLength(3), Validators.required])

  constructor(private formBuilder: FormBuilder,
              private reportGenerator: ReportGenerator,
              private router: Router) {
  }

  ngOnInit(): void {
    this.reportCriteria = this.formBuilder.group({
      reportId: this.reportId,
      characterName: this.characterName,
      planetName: this.planetName,
    });
  }

  generateReport() {
    const subscription = this.reportGenerator.generateReport(new ReportGeneratingCriteria(this.reportId.value, this.characterName.value, this.planetName.value))
      .pipe(switchMap(value => from(this.router.navigate(['/report/list']))),
        catchError(this.handleError))
      .subscribe(_ => subscription.unsubscribe());
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error.code}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
}
