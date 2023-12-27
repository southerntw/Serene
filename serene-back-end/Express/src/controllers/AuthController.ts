import { Request, Response } from "express";
import { db } from "../database/db";
import { users, NewUser } from "../database/schema/users";
import { eq } from "drizzle-orm";
import BadRequestError from "../errors/BadRequestError";
import ValidationError from "../errors/ValidationError";
import { encrypt } from "../utils/encryptor";
import { Result, validationResult, matchedData } from "express-validator";
import jwt from "jsonwebtoken";
import bcrypt from "bcrypt";

export class AuthController {
  public async register(req: Request, res: Response): Promise<void> {
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
      throw new ValidationError({
        message: "Missing required fields",
        logging: true,
        context: result.array(),
      });
    }

    const data = matchedData(req);
    const { name, email, password } = data;
    if (!name || !email || !password) {
      throw new BadRequestError({
        message: "Missing required fields",
        logging: true,
      });
    }

    const hashedPassword = await encrypt(password);
    const newUser: NewUser = {
      name: name,
      email: email,
      password: hashedPassword,
    };

    try {
      await db.insert(users).values(newUser);
      res.status(201).json({
        success: true,
        data: {
          name,
          email,
        },
      });
      return;
    } catch (err) {
      throw new Error(err);
    }
  }

  public async login(req: Request, res: Response) {
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
      throw new ValidationError({
        message: "Missing required fields",
        logging: true,
        context: result.array(),
      });
    }

    const data = matchedData(req);
    const { email, password } = data;

    try {
      const findUser = await db
        .select()
        .from(users)
        .where(eq(users.email, email))
        .limit(1);

      if (!findUser) {
        throw new BadRequestError({ message: "Invalid credentials" });
      }

      const user = findUser[0];

      const passwordIsValid = await bcrypt.compare(password, user.password);

      if (!passwordIsValid) {
        throw new BadRequestError({ message: "Invalid credentials" });
      }

      const payload = { id: user.id, name: user.name, email: user.email };
      const accessToken = jwt.sign(payload, process.env.JWT_SECRET, {
        expiresIn: "1d",
      });

      res.status(200).json({
        success: true,
        data: {
          id: user.id,
          name: user.name,
          email: user.email,
          access_token: accessToken,
        },
      });
      return;
    } catch (err) {
      console.log(err);
      throw new Error(err);
    }
  }
}
