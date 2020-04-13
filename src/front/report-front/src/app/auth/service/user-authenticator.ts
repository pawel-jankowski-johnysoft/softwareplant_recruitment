import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {switchMap, tap} from "rxjs/operators";
import {Router} from "@angular/router";

@Injectable({providedIn: 'any'})
export class UserAuthenticator {
  constructor(private http: HttpClient,
              private router: Router) {
  }

  authenticate(userDetails: UserDetails) {
    const subscription = this.http.post('/login', this.formData(userDetails))
      .pipe(tap(_ => localStorage.setItem('logged', 'true')),
        switchMap(_ => this.router.navigate(['/report/list'])))
      .subscribe(_ => subscription.unsubscribe());
  }

  private formData(userDetails: UserDetails): FormData {
    const formData = new FormData();
    formData.append('username', userDetails.username);
    formData.append('password', userDetails.password);
    return formData;
  }
}

export class UserDetails {
  constructor(public readonly username: string,
              public readonly password: string) {

  }
}
