import { drizzle } from "drizzle-orm/postgres-js";
import postgres from "postgres";
import * as dotenv from "dotenv";

dotenv.config();
if (!process.env.DATABASE_URL) {
  throw new Error("DATABASE_URL environment variable not set");
}

const queryClient = postgres(process.env.DATABASE_URL!);
export const db = drizzle(queryClient, { logger: true });
