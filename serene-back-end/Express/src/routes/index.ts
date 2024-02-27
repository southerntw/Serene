import { Router } from "express";
import verifyToken from "../middlewares/verifyToken";
import { auth, bot, user, thread, news, mood } from "../controllers";
import swaggerUi from "swagger-ui-express";
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

const router = Router();

router.get("/", (_req, res) => {
  res.send(
    "<h1>Welcome to Safe Space API</h1><p>Explore the API documentation <a href='/api/v1/docs'>here</a>.</p>",
  );
});

router.use(
  "/docs",
  swaggerUi.serve,
  swaggerUi.setup(undefined, {
    swaggerOptions: {
      url: "/swagger.json",
    },
  }),
);

router.post("/bot/chat", verifyToken, chatValidator, bot.sendChat);
router.get("/bot/encourage", verifyToken, bot.encourage);

router.put("/user", verifyToken, editProfileValidator, user.editUser); // Done
router.get("/user/:id", userIdValidator, user.getUser); // Done
router.get("/user/mood/:id", verifyToken, userIdValidator, mood.getMood);
router.post("/user/mood", verifyToken, addMoodValidator, mood.addMood);

router.post("/auth/login", loginValidator, auth.login); // Done
router.post("/auth/register", registerValidator, auth.register); // Done

router.get("/threads", thread.getThreads); // Done [BUTUH TITLE]
router.get("/thread/:id", getIdValidator, thread.getThread); // Done [TITLE]
router.post("/thread", verifyToken, postThreadValidator, thread.postThread);  // Done [TITLE]

router.get("/news", news.getNews); // Done
router.get("/news/:id", getIdValidator, news.readNews); // Done

export default router;