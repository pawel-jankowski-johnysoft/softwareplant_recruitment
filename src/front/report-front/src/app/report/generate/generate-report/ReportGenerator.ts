import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";

const CHARACTER_NAME = 'query_criteria_character_phrase';
const PLANET_NAME = 'query_criteria_planet_name';

@Injectable({providedIn: "any"})
export class ReportGenerator {
  constructor(private http: HttpClient) {
  }


  generateReport(criteria: ReportGeneratingCriteria) {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };

    return this.http.put(`/report/${criteria.reportId}`, {
      [CHARACTER_NAME]: criteria.characterName,
      [PLANET_NAME]: criteria.planetName
    }, httpOptions);
  }
}

export class ReportGeneratingCriteria {
  constructor(public readonly reportId: number,
              public readonly characterName: string,
              public readonly planetName: string) {
  }
}
