import { Request, Response } from "express";
import { db } from "../db/db";
import { eq } from "drizzle-orm";
import { moods, NewMood } from "../db/schema/moods";
import { validationResult, Result, matchedData } from "express-validator";
import BadRequestError from "../errors/BadRequestError";

export default class MoodController {
  public async getMood(req: Request, res: Response) {
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
      throw new BadRequestError({
        message: "invalid params",
        logging: true,
        context: result.array(),
      });
    }

    const id = Number(req.params.id);

    try {
      const userMoods = await db
        .select()
        .from(moods)
        .where(eq(moods.userId, id));
      res.json({ success: true, data: userMoods });
      return;
    } catch (err) {
      console.log(err);
      throw new Error(err);
    }
  }

  public async addMood(req: Request, res: Response) {
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
      throw new BadRequestError({
        message: "Missing required fields",
        logging: true,
        context: result.array(),
      });
    }

    const data = matchedData(req);

    const newMood: NewMood = {
      userId: data.userId,
      type: data.type,
      description: data.description,
      audio: data.audio,
    };

    try {
      await db.insert(moods).values(newMood);
      res.status(201).json({
        success: true,
        data: {
          newMood,
        },
      });
    } catch (err) {
      console.log(err);
      throw new Error(err);
    }
  }
}
