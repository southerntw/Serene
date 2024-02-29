import { NextFunction, Request, Response } from "express";
import { CustomError } from "../errors/CustomError";

export const errorHandler = (
  err: Error,
  _req: Request,
  res: Response,
  _next: NextFunction,
) => {
  if (err instanceof CustomError) {
    const { statusCode, errors, logging } = err;
    if (logging) {
      console.error(
        JSON.stringify(
          {
            code: err.statusCode,
            errors: err.errors,
            stack: err.stack,
          },
          null,
          2,
        ),
      );
    }

    return res.status(statusCode).send({ success: false, errors });
  } else if (err instanceof SyntaxError) {
    const statusCode = 400;
    const errors = { message: "Invalid JSON syntax in request body" };
    console.error(
      JSON.stringify(
        {
          code: statusCode,
          errors,
          stack: err.stack,
        },
        null,
        2,
      ),
    );
    return res.status(statusCode).send({ success: false, errors });
  }

  console.error(JSON.stringify(err, null, 2));
  console.log(`Error: ${err}`);
  return res
    .status(500)
    .send({
      success: false,
      errors: {
        message: "An error occurred. Please check your request and try again.",
      },
    });
};
