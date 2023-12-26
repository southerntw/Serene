import { Request, Response } from "express";

export const wildcardRoute = (req: Request, res: Response) => {
  const requestedEndpoint = req.originalUrl;
  res.status(404).send({
    success: false,
    errors: { message: `${requestedEndpoint} was not found` },
  });
};
