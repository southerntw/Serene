import { Request, Response } from "express";
import axios from "axios";

interface ChatSendResponse {
  success: boolean;
  chat?: string;
  sentiment?: boolean;
  message?: string;
}

export default class ChatController {
  public async sendChat(
    _req: Request,
    res: Response,
  ): Promise<ChatSendResponse> {
    const chatUrl = process.env.CHAT_API_URL + "/chat/send";
    const requestBody = {
      message: {
        userMessage: "Hello how are you?",
        userId: 69,
      },
    };

    try {
      const response = await axios.post(chatUrl, requestBody);
      res.json({
        success: true,
        chat: response.data.message,
        sentiment: response.data.sentiment,
      });
      return;
    } catch (error) {
      console.log("Error: ", error.message);
      res.json({
        success: false,
        message: error.message,
      });
      return;
    }
  }

  public async encourage(): Promise<void> {
    // TODO: Implement this
    return;
  }
}
