import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Report, ReportEntry} from "../../model/Report";
import {map} from "rxjs/operators";

@Injectable({providedIn: "any"})
export class ReportsProvider {
  constructor(private http: HttpClient) {
  }

  getAllReports(): Observable<Report[]> {
    return this.http.get("/report")
      .pipe(
        map((reports: any) => {
          return reports.map(report => {
            return new Report(report.report_id, report.query_criteria_character_phrase, report.query_criteria_planet_name,
              report.result.map(it => new ReportEntry(it.film_id, it.film_name, it.character_id, it.character_name, it.planet_id, it.planet_name)));
          });
        })
      );
  }
}
