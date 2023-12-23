import { CustomError } from "./CustomError";

export default class UnauthorizedError extends CustomError {
  private static readonly _statusCode = 401;
  private readonly _code: number;
  private readonly _context: { [key: string]: any };
  private readonly _logging: boolean;

  constructor(params?: {
    code?: number;
    message?: string;
    logging?: boolean;
    context?: { [key: string]: any };
  }) {
    const { code, message, logging } = params || {};

    super(message || "Bad request");
    this._code = code || UnauthorizedError._statusCode;
    this._logging = logging || false;
    this._context = params?.context || {};

    Object.setPrototypeOf(this, UnauthorizedError.prototype);
  }

  get errors() {
    return [{ message: this.message, context: this._context }];
  }

  get statusCode() {
    return this._code;
  }

  get logging() {
    return this._logging;
  }
}
