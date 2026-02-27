
export type ApiError = {
  status: number;
  userMessage: string;
  technicalMessage?: string;
  code?: string;
  traceId?: string;
  fieldErrors?: Record<string, string>;
};
