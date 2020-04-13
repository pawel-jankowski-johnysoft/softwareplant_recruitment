/* "Barrel" of Http Interceptors */
import {HTTP_INTERCEPTORS} from '@angular/common/http';

import {UnauthorizedRedirectInterceptor} from './unauthorized-redirect-interceptor.service';

/** Http interceptor providers in outside-in order */
export const httpInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: UnauthorizedRedirectInterceptor, multi: true},
];
