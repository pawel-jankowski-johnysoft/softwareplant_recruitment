import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserAuthenticator, UserDetails} from "../service/user-authenticator";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginFormGroup: FormGroup;
  username: FormControl = new FormControl('', Validators.required);
  password: FormControl = new FormControl('', Validators.required);

  constructor(private readonly formBuilder: FormBuilder,
              private readonly userAuthenticator: UserAuthenticator) {
  }

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      username: this.username,
      password: this.password
    });
  }

  signIn() {
    this.userAuthenticator.authenticate(new UserDetails(this.username.value, this.password.value));
  }
}
