import { Router } from "express";
import verifyToken from "../middlewares/verifyToken";
import {
  registerValidator,
  loginValidator,
  getIdValidator,
  chatValidator,
  addMoodValidator,
  postThreadValidator,
  editProfileValidator,
  userIdValidator,
} from "../middlewares/requestValidators";
import * as Controllers from "../controllers";

const router = Router();
const auth = new Controllers.AuthController();
const user = new Controllers.UserController();
const bot = new Controllers.BotController();
const mood = new Controllers.MoodController();
const thread = new Controllers.ThreadController();
const news = new Controllers.NewsController();

router.get("/", (_req, res) => {
  res.send("Safe Space API");
});

router.post("/v1/bot/send", verifyToken, chatValidator, bot.sendChat);
router.get("/v1/bot/encourage", verifyToken, bot.encourage);

router.put("/v1/user", verifyToken, editProfileValidator, user.editUser);
router.get("/v1/user/:id", userIdValidator, user.getUser);
router.get("/v1/user/mood/:id", verifyToken, userIdValidator, mood.getMood);
router.post("/v1/user/mood", verifyToken, addMoodValidator, mood.addMood);

router.post("/v1/auth/login", loginValidator, auth.login);
router.post("/v1/auth/register", registerValidator, auth.register);

router.get("/v1/threads", thread.getThreads);
router.get("/v1/thread/:id", getIdValidator, thread.getThread);
router.post("/v1/thread", verifyToken, postThreadValidator, thread.postThread);

router.get("/v1/news", news.getNews);
router.get("/v1/news/:id", getIdValidator, news.readNews);

export default router;
