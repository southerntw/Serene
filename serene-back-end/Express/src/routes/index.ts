import { Router } from "express";
import Auth from "../controllers/AuthController";
import Bot from "../controllers/BotController";
import verifyToken from "../middlewares/verifyToken";
import {
  registerValidator,
  loginValidator,
  chatValidator,
} from "../middlewares/requestValidators";

const router = Router();

const auth = new Auth();
const bot = new Bot();

router.get("/", (_req, res) => {
  res.send("Safe Space API");
});

router.post("/v1/bot/send", verifyToken, chatValidator, bot.sendChat);
router.get("/v1/bot/encourage", verifyToken, bot.encourage);

router.get("/v1/user");
router.put("/v1/user");
router.get("/v1/user/{id}");
router.get("/v1/user/mood");
router.post("/v1/user/mood");

router.post("/v1/auth/login", loginValidator, auth.login);
router.post("/v1/auth/register", registerValidator, auth.register);

router.get("/v1/forum");
router.get("/v1/forum/{id}");
router.post("/v1/forum");

router.get("/v1/news");
router.get("/v1/news/{id}");

export default router;
