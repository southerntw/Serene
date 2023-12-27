import { Request, Response } from "express";
import { db } from "../database/db";
import { eq } from "drizzle-orm";
import { users } from "../database/schema/users";
import { validationResult, Result, matchedData } from "express-validator";
import BadRequestError from "../errors/BadRequestError";
import ValidationError from "../errors/ValidationError";

export class UserController {
  public async getUser(req: Request, res: Response) {
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
      throw new BadRequestError({
        message: "invalid params",
        logging: true,
        context: result.array(),
      });
    }

    const userId = req.params.id;

    try {
      const usersData = await db
        .select()
        .from(users)
        .where(eq(users.id, userId));
      const user = usersData[0];
      res.json({
        success: true,
        data: {
          id: user.id,
          name: user.name,
          email: user.email,
          avatar: user.avatar,
          about: user.about,
          birthdate: user.birthdate,
        },
      });
      return;
    } catch (err) {
      console.log(err);
      throw new Error(err);
    }
  }

  public async editUser(req: Request, res: Response) {
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
    try {
      await db
        .update(users)
        .set({
          id: data.id,
          name: data.name,
          email: data.email,
          password: data.password,
          avatar: data.avatar,
          about: data.about,
          birthdate: data.birthdate,
        })
        .where(eq(users.id, data.id));
      res.json({
        success: true,
        data: {
          id: data.id,
          name: data.name,
          email: data.email,
          avatar: data.avatar,
          about: data.about,
          birthdate: data.birthdate,
        },
      });
      return;
    } catch (err) {
      console.log(err);
      throw new Error(err);
    }
  }
}
