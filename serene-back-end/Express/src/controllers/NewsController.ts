import { Request, Response } from "express";
import { db } from "../database/db";
import { eq } from "drizzle-orm";
import { news } from "../database/schema/news";
import { validationResult, Result } from "express-validator";
import BadRequestError from "../errors/BadRequestError";

export class NewsController {
  public async getNews(req: Request, res: Response) {
    const page = req.query.page ? parseInt(req.query.page as string) : 1;
    const limit = 10; // Number of news threads per page
    const offset = (page - 1) * limit;

    try {
      const newsThreads = await db
        .select()
        .from(news)
        .limit(limit)
        .offset(offset);

      res.json({ success: true, data: newsThreads });
      return;
    } catch (err) {
      console.log(err);
      throw new Error(err);
    }
  }

  public async readNews(req: Request, res: Response) {
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
      throw new BadRequestError({
        message: "invalid params",
        logging: true,
        context: result.array(),
      });
    }

    const newsId = Number(req.params.id);

    try {
      const newsData = await db.select().from(news).where(eq(news.id, newsId));
      const threadData = newsData[0];
      res.json({ success: true, data: threadData });
      return;
    } catch (err) {
      console.log(err);
      throw new Error(err);
    }
  }
}
