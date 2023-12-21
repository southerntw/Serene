import express from "express";
import router from "./routes/";
import swaggerUi from "swagger-ui-express";
import morgan from "morgan";

import * as dotenv from "dotenv";

dotenv.config();

const app = express();
const port = 3000;

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

app.listen(port, () => {
  return console.log(`Express is listening at http://localhost:${port}`);
});
