import express from "express";
import * as dotenv from "dotenv";
import cors from "cors";
import morgan from "morgan";
import "express-async-errors";

import { errorHandler } from "./middlewares/errors";
import { wildcardRoute } from "./middlewares/wildcardRoute";
import router from "./routes/";

dotenv.config();
const app = express();

app.use(cors({ credentials: true, origin: `*` }));
app.use(express.json());
app.use(morgan("tiny"));
app.use(express.static("public"));
app.use("/api/v1", router);
app.use(errorHandler);
app.get("*", wildcardRoute);

const port = 3060;
app.listen(port, () => {
  return console.log(`Express is listening at http://localhost:${port}`);
});
