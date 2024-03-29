import { Request, Response } from "express";
import { db } from "../database/db";
import { threads, NewThread } from "../database/schema/threads";
import { eq } from "drizzle-orm";
import { validationResult, Result, matchedData } from "express-validator";
import BadRequestError from "../errors/BadRequestError";
import ValidationError from "../errors/ValidationError";

export class ThreadController {
  public async getThreads(req: Request, res: Response) {
    const page = req.query.page ? parseInt(req.query.page as string) : 1;
    const limit = 10;
    const offset = (page - 1) * limit;

    try {
      const forumThreads = await db
        .select()
        .from(threads)
        .limit(limit)
        .offset(offset);
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
      title: data.title,
      text: data.text,
      tag: data.tag,
      threadStarter: data.threadStarter,
    };
    try {
      await db.insert(threads).values(newThread);
      res.status(201).json({
        success: true,
        data: {
          title: data.title,
          text: newThread.text,
          tag: newThread.tag,
          threadStarter: newThread.threadStarter,
        },
      });
      return;
    } catch (err) {
      console.log(err);
      throw new Error(err);
    }
  }
}
