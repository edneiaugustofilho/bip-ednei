import {FormGroup} from '@angular/forms';

export function normalizeCurrency(fg: FormGroup, controlName: string): void {
  const control = fg.get(controlName);
  if (!control || control.value == null || control.value === '') return;

  const n = typeof control.value === 'number'
    ? control.value
    : Number(String(control.value).replace(/\./g, '').replace(',', '.'));

  if (!Number.isFinite(n)) return;

  const fixed = n.toFixed(2); // "150.00"

  control.setValue(fixed, { emitEvent: false });
}
