import { Get, Route } from "tsoa";
import axios from "axios";

interface ChatSendResponse {
  message?: string;
  sentiment?: boolean;
}

@Route("chat")
export default class ChatController {
  @Get("send")
  public async sendChat(): Promise<ChatSendResponse> {
    const chatUrl = process.env.CHAT_API_URL + "/chat/send";
    const requestBody = {
      message: {
        userMessage: "Hello how are you?",
        userId: 69,
      },
    };

    try {
      const response = await axios.post(chatUrl, requestBody);
      return {
        message: response.data.message,
        sentiment: response.data.sentiment,
      };
    } catch (error) {
      console.log("Error: ", error.message);
      return {
        message: "Error",
      };
    }
  }
}
