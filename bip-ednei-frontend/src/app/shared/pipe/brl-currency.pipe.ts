import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'brlCurrency',
  standalone: true,
})
export class BrlCurrencyPipe implements PipeTransform {

  transform(value: number | string | null | undefined, digits: string = '1.2-2'): string {
    const n = typeof value === 'string' ? Number(value) : value;

    if (n === null || n === undefined || Number.isNaN(n)) {
      return 'R$ 0,00';
    }

    // digits like "1.2-2" (we only care about fraction part "2-2")
    const fraction = digits.split('.')[1] ?? '2-2';
    const [minFracRaw, maxFracRaw] = fraction.split('-');
    const minFrac = Number(minFracRaw);
    const maxFrac = Number(maxFracRaw);

    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL',
      minimumFractionDigits: Number.isFinite(minFrac) ? minFrac : 2,
      maximumFractionDigits: Number.isFinite(maxFrac) ? maxFrac : 2,
    }).format(n);
  }
}
