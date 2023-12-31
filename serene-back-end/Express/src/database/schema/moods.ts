import {
  pgTable,
  serial,
  varchar,
  timestamp,
  pgEnum,
  uuid,
} from "drizzle-orm/pg-core";
import { users } from "./users";

export const moodEnum = pgEnum("mood", [
  "very sad",
  "sad",
  "neutral",
  "happy",
  "very happy",
]);

export const moods = pgTable("moods", {
  id: serial("id").primaryKey(),
  date: timestamp("date").defaultNow().notNull(),
  type: moodEnum("type").notNull(),
  description: varchar("description"),
  audio: varchar("audio"),
  userId: uuid("user_id").references(() => users.id),
});

export type Mood = typeof moods.$inferSelect;
export type NewMood = typeof moods.$inferInsert;
