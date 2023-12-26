export type CustomErrorContent = {
    message: string;
    context?: { [key: string]: any };
};

export abstract class CustomError extends Error {
  public abstract statusCode: number;
  public abstract errors: CustomErrorContent;
  public abstract logging: boolean;

  constructor(message: string) {
    super(message);
    Object.setPrototypeOf(this, CustomError.prototype);
  }
}
