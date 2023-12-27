import { pgTable, serial, varchar, uuid } from "drizzle-orm/pg-core";
import { users } from "./users";

export const threads = pgTable("threads", {
  id: serial("id").primaryKey(),
  text: varchar("text").notNull(),
  tag: varchar("tag"),
  threadStarter: uuid("thread_starter").references(() => users.id),
});

export type Thread = typeof threads.$inferSelect;
export type NewThread = typeof threads.$inferInsert;
