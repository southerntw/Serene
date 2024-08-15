import { Request, Response } from "express";
import axios from "axios";
import { Result, validationResult, matchedData } from "express-validator";
import ValidationError from "../errors/ValidationError";
import { db } from "../database/db";
import { chats, NewChat } from "../database/schema/chats";
import { eq } from "drizzle-orm";
import { news } from "../database/schema/news";

interface ChatSendResponse {
  success: boolean;
  chat?: string;
  sentiment?: boolean;
  message?: string;
}

export class BotController {
  public async sendChat(
    req: Request,
    res: Response,
  ): Promise<ChatSendResponse> {
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
      throw new ValidationError({
        message: "Missing required fields",
        logging: true,
        context: result.array(),
      });
    }
    const data = matchedData(req);

    const requestBody = {
      userMessage: data.message,
      userId: data.userId,
    };

    try {
      const chatUrl = process.env.CHAT_API_URL + "/chat/send/";
      console.log(chatUrl);
      console.log(requestBody);
      const response = await axios.post(chatUrl, requestBody);
      res.json({
        success: true,
        data: {
          chat: response.data.message,
          sentiment: response.data.sentiment,
        },
      });
      return;
    } catch (error) {
      throw new Error(error);
    }
  }

  public async encourage(req: Request, res: Response) {
    const result: Result = validationResult(req);
    if (!result.isEmpty()) {
      throw new ValidationError({
        message: "Missing required fields",
        logging: true,
        context: result.array(),
      });
    }
    const data = matchedData(req);

    const requestBody = {
      userId: data.userId,
    };

    try {
      const encourageUrl = process.env.CHAT_API_URL + "/chat/encourage/";
      console.log(encourageUrl);
      console.log(requestBody);
      const response = await axios.post(encourageUrl, requestBody);
      const newsRecommendations = await db
        .select()
        .from(news)
        .limit(5);

      res.json({
        success: true,
        data: {
          message: response.data.message,
          recommendations: newsRecommendations
        },
      });
      return;
    } catch (error) {
      throw new Error(error);
    }
  }
}
