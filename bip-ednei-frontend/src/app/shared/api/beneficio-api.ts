import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment.development';
import {Observable} from 'rxjs';
import {BeneficioDto} from '../dto/beneficio-dto';
import {TransferRequest} from '../dto/transfer-request';

@Injectable({
  providedIn: 'root',
})
export class BeneficioApi {
  private readonly http = inject(HttpClient);

  private readonly baseUrl = `${environment.apiBaseUrl}`;

  list() {
    return this.http.get<BeneficioDto[]>('/api/v1/beneficios');
  }

  transfer(request: TransferRequest): Observable<void> {
    return this.http.post<void>('/api/v1/beneficios/transfer', request);
  }

}
