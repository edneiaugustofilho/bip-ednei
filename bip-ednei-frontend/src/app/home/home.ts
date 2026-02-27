import {Component, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {shareReplay} from 'rxjs/operators';

import {BeneficioApi} from '../shared/api/beneficio-api';
import {BeneficioDto} from '../shared/dto/beneficio-dto';
import {Observable} from 'rxjs';
import {buildTransferForm} from '../feature/tranfer/transfer-form.factory';
import {TransferRequest} from '../shared/dto/transfer-request';
import {ApiError} from '../shared/http/api-error';
import {applyApiErrors} from '../shared/http/apply-api-errors';
import {ToastService} from '../shared/toast/toast';
import {normalizeCurrency} from '../shared/normalizers.util';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {NgxMaskDirective} from 'ngx-mask';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    NgxMaskDirective,
  ],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class HomeComponent {

  private fb = inject(FormBuilder);
  private beneficioApi = inject(BeneficioApi);
  private toastService: ToastService = inject(ToastService);

  form = buildTransferForm(this.fb);

  beneficios$: Observable<BeneficioDto[]> = this.beneficioApi.list().pipe(shareReplay(1));

  transfer(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request: TransferRequest = this.form.getRawValue() as TransferRequest;

    this.beneficioApi.transfer(request).subscribe({
      next: () => {
        this.toastService.concludedWithSuccess();
        this.form.reset();
      },
      error: (apiError: ApiError) => {
        applyApiErrors(this.form, apiError, this.toastService);
      }
    });
  }

  protected readonly normalizeCurrency = normalizeCurrency;
}
