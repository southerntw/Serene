import { Router } from "express";
import Chat from "../controllers/ChatController";

const router = Router();
const chat = new Chat();

router.get("/", (_req, res) => {
  res.send("Hello World!");
});

router.get("/chat/send", chat.sendChat);

export default router;
