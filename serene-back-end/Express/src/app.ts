import express from "express";
import * as dotenv from "dotenv";
import cors from "cors";
import swaggerUi from "swagger-ui-express";
import morgan from "morgan";
import "express-async-errors";

import { errorHandler } from "./middlewares/errors";
import router from "./routes/";

dotenv.config();
const app = express();

app.use(cors({ credentials: true, origin: `*` }));
app.use(express.json());
app.use(morgan("tiny"));
app.use(express.static("public"));

app.use(
  "/docs",
  swaggerUi.serve,
  swaggerUi.setup(undefined, {
    swaggerOptions: {
      url: "/swagger.json",
    },
  }),
);

app.use(router);
app.use(errorHandler);

const port = 3000;
app.listen(port, () => {
  return console.log(`Express is listening at http://localhost:${port}`);
});
