import { drizzle } from "drizzle-orm/postgres-js";
import { migrate } from "drizzle-orm/postgres-js/migrator";
import { fileURLToPath } from "url";

import path from "path";
import postgres from "postgres";
import * as dotenv from "dotenv";

console.log("Finding environemnt variables");

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
dotenv.config({ path: __dirname + "/../../.env" });
console.log(process.env.DATABASE_URL);

console.log("Running migrations");

const migrationClient = postgres(process.env.DATABASE_URL!, { max: 1 });
const db = drizzle(migrationClient);
await migrate(db, { migrationsFolder: "src/db/schema" });
await migrationClient.end();
