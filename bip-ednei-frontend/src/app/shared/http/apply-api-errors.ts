
import {AbstractControl, FormGroup} from '@angular/forms';

export type ApiError = { userMessage: string; fieldErrors?: Record<string, string> };

export function applyApiErrors(
  form: FormGroup,
  e: ApiError,
  toast?: { error: (msg: string) => void }
): void {
  toast?.error(e.userMessage ?? 'Erro inesperado.');

  if (!e.fieldErrors) return;

  for (const [field, message] of Object.entries(e.fieldErrors)) {
    const control = findControl(form, field);
    if (!control) continue;

    control.setErrors({ ...(control.errors ?? {}), api: message });
    control.markAsTouched();
    control.markAsDirty();
  }
}

function findControl(form: FormGroup, path: string): AbstractControl | null {
  const normalized = path.replace(/\[(\d+)]/g, '.$1');
  return form.get(normalized);
}
