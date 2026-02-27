import {ApplicationConfig} from '@angular/core';
import {provideRouter} from '@angular/router';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {httpErrorInterceptor} from './shared/http/http-error.interceptor';

import {provideNativeDateAdapter} from '@angular/material/core';
import {provideNgxMask} from 'ngx-mask';
import {routes} from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideNativeDateAdapter(),
    provideNgxMask({
      dropSpecialCharacters: false,
    }),
    provideHttpClient(
      withInterceptors([httpErrorInterceptor])
    ),
  ],
};
