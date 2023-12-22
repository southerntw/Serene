import { Request, Response } from "express";
import { users, NewUser } from "../db/schema/users";
import { db } from "../db/db";
import BadRequestError from "../errors/BadRequestError";
import ValidationError from "../errors/ValidationError";
import { encrypt } from "../helpers/encryptor";
import { Result, validationResult, matchedData } from "express-validator";

export default class AuthController {
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
    console.log(data);

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
    } catch (err) {
      throw new Error(err);
    }
  }

  public async login(_req: Request, res: Response) {
    res.status(501).json({ success: false, message: "Not implemented" });
    return;
  }
}
