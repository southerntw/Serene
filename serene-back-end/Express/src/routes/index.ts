import { Router } from "express";
import Auth from "../controllers/AuthController";

const router = Router();

const auth = new Auth();

router.get("/", (_req, res) => {
  res.send("Safe Space API. Made with Express.js");
});

router.get("/bot/send");
router.get("/bot/encourage");

router.get("/user");
router.put("/user");
router.get("/user/{id}");
router.get("/user/mood");
router.post("/user/mood");

router.post("/auth/login");
router.post("/auth/register", auth.register);

router.get("/forum");
router.get("forum/{id}");
router.post("/forum");

router.get("/news");
router.get("/news/{id}");

export default router;
