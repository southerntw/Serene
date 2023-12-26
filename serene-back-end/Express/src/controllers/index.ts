import * as Auth from "./AuthController";
import * as Bot from "./BotController";
import * as Mood from "./MoodController";
import * as Thread from "./ThreadController";
import * as News from "./NewsController";
import * as User from "./UserController";

export const auth = new Auth.AuthController();
export const bot = new Bot.BotController();
export const mood = new Mood.MoodController();
export const thread = new Thread.ThreadController();
export const news = new News.NewsController();
export const user = new User.UserController();
