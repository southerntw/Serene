import jwt from "jsonwebtoken";
import { Request, Response, NextFunction } from "express";
import UnauthorizedError from "../errors/UnauthorizedError";

export default function verifyToken(
  req: Request,
  _res: Response,
  next: NextFunction,
) {
  const authHeader = req.headers.authorization;
  if (!authHeader) {
    throw new UnauthorizedError({ message: "No token provided" });
  }
  const token = authHeader.split(" ")[1];

  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    if (!decoded) {
      throw new UnauthorizedError({ message: "Invalid token" });
    }
    next();
  } catch (err) {
    throw new UnauthorizedError({ message: "Invalid token" });
  }
}
