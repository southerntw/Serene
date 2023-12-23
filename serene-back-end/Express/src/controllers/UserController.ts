import { Request, Response } from "express";
import { db } from "../db/db";
import { eq } from "drizzle-orm";
import { users, NewUser } from "../db/schema/users";
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

    const userId = Number(req.params.id);

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

    // TODO: Edit user schema first then implement this
    res.json(501).send("Not implemented");
    return;
  }
}
