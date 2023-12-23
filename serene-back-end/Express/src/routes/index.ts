import { Router } from "express";
import Auth from "../controllers/AuthController";
import Bot from "../controllers/BotController";
import Mood from "../controllers/MoodController";
import Thread from "../controllers/ThreadController";
import News from "../controllers/NewsController";
import User from "../controllers/UserController";
import verifyToken from "../middlewares/verifyToken";
import {
  registerValidator,
  loginValidator,
  chatValidator,
  getIdValidator,
  addMoodValidator,
  postThreadValidator,
} from "../middlewares/requestValidators";

const router = Router();
const auth = new Auth();
const user = new User();
const bot = new Bot();
const mood = new Mood();
const thread = new Thread();
const news = new News();

router.get("/", (_req, res) => {
  res.send("Safe Space API");
});

router.post("/v1/bot/send", verifyToken, chatValidator, bot.sendChat);
router.get("/v1/bot/encourage", verifyToken, bot.encourage);

router.put("/v1/user", verifyToken, user.editUser);
router.get("/v1/user/:id", getIdValidator, user.getUser);
router.get("/v1/user/mood/:id", verifyToken, getIdValidator, mood.getMood);
router.post("/v1/user/mood", verifyToken, addMoodValidator, mood.addMood);

router.post("/v1/auth/login", loginValidator, auth.login);
router.post("/v1/auth/register", registerValidator, auth.register);

router.get("/v1/threads", thread.getThreads);
router.get("/v1/thread/:id", getIdValidator, thread.getThread);
router.post("/v1/thread", verifyToken, postThreadValidator, thread.postThread);

router.get("/v1/news", news.getNews);
router.get("/v1/news/:id", getIdValidator, news.readNews);

export default router;
