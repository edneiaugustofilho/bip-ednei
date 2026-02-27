import {FormBuilder, Validators} from '@angular/forms';

export function buildTransferForm(fb: FormBuilder) {
  return fb.group({
    fromId: fb.control<number | null>(null, [Validators.required]),
    toId: fb.control<number | null>(null, [Validators.required]),
    amount: fb.control<number | null>(null, [Validators.required]),
  })
}
