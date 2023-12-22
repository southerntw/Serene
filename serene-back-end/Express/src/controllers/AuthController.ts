import bcrypt from "bcrypt";
import { Request, Response } from "express";
import { users, NewUser } from "../db/schema/users";
import { db } from "../db/db";

interface RegisterResponse {
  success: boolean;
  data?: {
    name: string;
    email: string;
  };
  message?: string;
}

export default class AuthController {
  public async register(
    req: Request,
    res: Response,
  ): Promise<RegisterResponse> {
    const { name, email, password } = req.body;
    if (!name || !email || !password) {
      res.json({
        success: false,
        message: "Please provide all fields",
      });
      return;
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
      res.json({
        success: true,
        data: {
          name,
          email,
        },
      });
    } catch (err) {
      res.json({
        success: false,
        message: err.message,
      });
    }
  }
}
