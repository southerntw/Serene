import { Request, Response } from "express";
import axios from "axios";
import { Result, validationResult, matchedData } from "express-validator";
import ValidationError from "../errors/ValidationError";

interface ChatSendResponse {
  success: boolean;
  chat?: string;
  sentiment?: boolean;
  message?: string;
}

export default class BotController {
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
      message: {
        userMessage: data.message,
        userId: data.userId,
      },
    };

    try {
      const chatUrl = process.env.CHAT_API_URL + "/chat/send";
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

  public async encourage(_req: Request, res: Response) {
    console.log("trying to encourage");
    try {
      res.json({ message: "not yet implemented." });
      return;
    } catch (error) {
      throw new Error(error);
    }
  }
}
