import bcrypt from "bcrypt";
import { Request, Response } from "express";
import { users, NewUser } from "../db/schema/users";
import { db } from "../db/db";
import BadRequestError from "../errors/BadRequestError";

export default class AuthController {
  public async register(req: Request, res: Response): Promise<void> {
    const { name, email, password } = req.body;
    if (!name || !email || !password) {
      throw new BadRequestError({
        code: 400,
        message: "Missing required fields",
        logging: true,
      });
    }
    const salt = await bcrypt.genSalt(10);
    const hashedPassword = await bcrypt.hash(password, salt);
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
