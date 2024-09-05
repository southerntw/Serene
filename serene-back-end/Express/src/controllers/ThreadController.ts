import { Request, Response } from "express";
import { db } from "../database/db";
import { threads, NewThread } from "../database/schema/threads";
import { comments, NewComment } from "../database/schema/comments";
import { users} from "../database/schema/users";
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
            .select({
                id: threads.id,
                title: threads.title,
                text: threads.text,
                tag: threads.tag,
                threadStarter: users.name,
            })
            .from(threads)
            .leftJoin(users, eq(threads.threadStarter, users.id))
            .limit(limit)
            .offset(offset)
            .execute();

        res.json({ success: true, data: forumThreads });
    } catch (err) {
        console.log(err);
        res.status(500).json({ success: false, error: err.message });
    }
  }

  public async getThread(req: Request, res: Response) {
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
        throw new BadRequestError({
            message: "Invalid parameters",
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

        if (!threadData) {
            return res.status(404).json({
                success: false,
                message: "Thread not found",
            });
        }

        const threadComments = await db
            .select({
                id: comments.id,
                comment: comments.comment,
                userId: comments.userId,
                threadId: comments.threadId,
                userName: users.name, // Assuming you want to include the user's name
            })
            .from(comments)
            .leftJoin(users, eq(comments.userId, users.id))
            .where(eq(comments.threadId, threadId))
            .execute();

        const data = {
            thread: threadData,
            comments: threadComments,
        };

        res.json({ success: true, data });
    } catch (err) {
        console.error(err);
        res.status(500).json({
            success: false,
            message: "Internal server error",
        });
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

  public async commentThread(req: Request, res: Response) {
    // Validate the request data
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
      throw new ValidationError({
        message: "Missing required fields",
        logging: true,
        context: result.array(),
      });
    }

    // Extract the validated data
    const data = matchedData(req);
    const newComment: NewComment = {
      comment: data.comment,
      userId: data.userId, // assuming the input field is `user_id`
      threadId: data.threadId, // assuming the input field is `thread_id`
    };

    try {
      // Insert the new comment into the database
      await db.insert(comments).values(newComment);
      res.status(201).json({
        success: true,
        data: {
          id: newComment.id,
          comment: newComment.comment,
          user_id: newComment.userId,
          thread_id: newComment.threadId,
        },
      });
    } catch (err) {
      console.log(err);
      res.status(500).json({ success: false, error: err.message });
    }
  }

  public async getComments(req: Request, res: Response) {
    const threadId = Number(req.params.threadId);

    try {
      const threadComments = await db
        .select({
          id: comments.id,
          comment: comments.comment,
          userId: comments.userId,
          threadId: comments.threadId,
          userName: users.name, // Assuming you want to include the user's name
        })
        .from(comments)
        .leftJoin(users, eq(comments.userId, users.id))
        .where(eq(comments.threadId, threadId))
        .execute();

      if (threadComments.length === 0) {
        return res.status(404).json({
          success: false,
          message: "No comments found for this thread",
        });
      }

      res.json({ success: true, data: threadComments });
    } catch (err) {
      console.log(err);
      res.status(500).json({ success: false, error: err.message });
    }
  }
}
