import { Request, Response } from "express";
import axios from "axios";
import { Result, validationResult, matchedData } from "express-validator";
import ValidationError from "../errors/ValidationError";
import { db } from "../database/db";
import { chats, NewChat } from "../database/schema/chats";
import { eq } from "drizzle-orm";

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

    // If forumData is empty, create a new chat
    const forumData = await db
      .select()
      .from(chats)
      .where(eq(chats.userId, data.userId));
    let threadData = forumData[0];
    var chatData =
      "{'role':'user', 'parts':[ 'From now on, please talk like you're my mental health friend, talk no longer than 150 words, as if you're chatting with me on social media. DO NOT TALK with role and parts like I do. DO NOT USE Json Structure.' ]}, {'role':'model', 'parts':[ 'Okay! will do. ]}";

    if (forumData.length === 0) {
      chatData += `,{'role': 'user', 'parts': ['${data.message}']}`;

      var newChat: NewChat = {
        userId: data.userId,
        history: chatData,
      };

      threadData = newChat as any;
      await db.insert(chats).values(newChat);
    } else {
      chatData =
        forumData[0].history +
        `,{'role': 'user', 'parts': ['${data.message}']}`;
    }

    const requestBody = {
      userMessage: chatData,
      lastMessage: data.message,
      userId: data.userId,
    };

    try {
      const chatUrl = process.env.CHAT_API_URL + "/chat/send";
      console.log(requestBody);
      const response = await axios.post(chatUrl, requestBody);
      res.json({
        success: true,
        data: {
          chat: response.data.message,
          sentiment: response.data.sentiment,
        },
      });
      await db
        .update(chats)
        .set({
          history:
            chatData +
            `,{'role': 'model', 'parts': ['${response.data.message}']}`,
        })
        .where(eq(chats.userId, data.userId));
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
