
import {HttpErrorResponse} from '@angular/common/http';
import {ApiError} from './api-error';

export function mapHttpError(err: unknown): ApiError {
  console.log('error');
  console.log(err);


  if (!(err instanceof HttpErrorResponse)) {
    return { status: 0, userMessage: 'Erro inesperado.', technicalMessage: String(err) };
  }

  // Network / CORS / offline
  if (err.status === 0) {
    return { status: 0, userMessage: 'Sem conexão com o servidor. Verifique sua internet/VPN.' };
  }

  const body: any = err.error;

  const detail = body?.detail ?? body?.message ?? body?.error ?? null;
  const title  = body?.title ?? null;

  const fieldErrors =
    body?.fieldErrors ??
    body?.errors?.reduce?.((acc: any, e: any) => {
      // common shapes: { field, message } or { name, reason }
      const field = e.field ?? e.name;
      const msg = e.message ?? e.reason;
      if (field && msg) acc[field] = msg;
      return acc;
    }, {} as Record<string, string>);

  const defaultByStatus: Record<number, string> = {
    400: 'Requisição inválida.',
    401: 'Sua sessão expirou. Faça login novamente.',
    403: 'Você não tem permissão para essa ação.',
    404: 'Registro não encontrado.',
    409: 'Conflito: esse registro já existe ou foi alterado por outra pessoa.',
    422: 'Há campos inválidos. Verifique o formulário.',
    500: 'Erro interno no servidor.'
  };

  const userMessage =
    (typeof detail === 'string' && detail.trim()) ||
    (typeof title === 'string' && title.trim()) ||
    defaultByStatus[err.status] ||
    `Erro HTTP ${err.status}.`;

  return {
    status: err.status,
    userMessage,
    technicalMessage: err.message,
    code: body?.code,
    traceId: body?.traceId,
    fieldErrors
  };
}
