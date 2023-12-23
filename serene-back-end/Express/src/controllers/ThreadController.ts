import { Request, Response } from "express";
import { db } from "../db/db";
import { eq } from "drizzle-orm";
import { threads, NewThread } from "../db/schema/threads";
import { validationResult, Result, matchedData } from "express-validator";
import BadRequestError from "../errors/BadRequestError";
import ValidationError from "../errors/ValidationError";

export class ThreadController {
  public async getThreads(_req: Request, res: Response) {
    try {
      const forumThreads = await db.select().from(threads);
      res.json({ success: true, data: forumThreads });
      return;
    } catch (err) {
      console.log(err);
      throw new Error(err);
    }
  }

  public async getThread(req: Request, res: Response) {
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
      throw new BadRequestError({
        message: "invalid params",
        logging: true,
        context: result.array(),
      });
    }

    const threadId = Number(req.params.id);

    try {
      const forumData = await db
        .select()
        .from(threads)
        .where(eq(threads.id, threadId));
      const threadData = forumData[0];
      res.json({ success: true, data: threadData });
      return;
    } catch (err) {
      console.log(err);
      throw new Error(err);
    }
  }

  public async postThread(req: Request, res: Response) {
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
      throw new ValidationError({
        message: "Missing required fields",
        logging: true,
        context: result.array(),
      });
    }
    const data = matchedData(req);
    const newThread: NewThread = {
      text: data.text,
      tag: data.tag,
      threadStarter: data.threadStarter,
    };
    try {
      await db.insert(threads).values(newThread);
      res.status(201).json({
        success: true,
        data: {
          newThread,
        },
      });
      return;
    } catch (err) {
      console.log(err);
      throw new Error(err);
    }
  }
}
