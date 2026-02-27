import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export type ToastType = 'success' | 'error' | 'info' | 'warning';

export interface Toast {
  id: string;
  type: ToastType;
  message: string;
  durationMs: number;
}

@Injectable({ providedIn: 'root' })
export class ToastService {

  private readonly _toasts$ = new BehaviorSubject<Toast[]>([]);
  readonly toasts$ = this._toasts$.asObservable();

  show(message: string, type: ToastType = 'info', durationMs = 3000) {
    console.log('ToastService #1');
    console.log('message:' + message);
    console.log('type:' + type);

    const toast: Toast = {
      id: (typeof crypto !== 'undefined' && 'randomUUID' in crypto)
        ? crypto.randomUUID()
        : String(Date.now() + Math.random()),
      type,
      message,
      durationMs
    };

    console.log("toast:");
    console.log(toast);

    this._toasts$.next([...this._toasts$.value, toast]);

    window.setTimeout(() => this.dismiss(toast.id), durationMs);
  }

  dismiss(id: string) {
    this._toasts$.next(this._toasts$.value.filter(t => t.id !== id));
  }

  success(msg: string, durationMs = 3000) { this.show(msg, 'success', durationMs); }
  concludedWithSuccess(durationMs = 3000) { this.show('Concluído com sucesso', 'success', durationMs); }
  error(msg: string, durationMs = 4000) { this.show(msg, 'error', durationMs); }
  info(msg: string, durationMs = 3000) { this.show(msg, 'info', durationMs); }
  warning(msg: string, durationMs = 3500) { this.show(msg, 'warning', durationMs); }
}
