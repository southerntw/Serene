import { pgTable, serial, varchar, integer } from "drizzle-orm/pg-core";
import { users } from "./users";

// TODO: Change the schema. History might not be varchar.
export const chats = pgTable("chats", {
  id: serial("id").primaryKey(),
  history: varchar("history"),
  userId: integer("user_id").references(() => users.id),
});

export type Chat = typeof chats.$inferSelect;
export type NewChat = typeof chats.$inferInsert;
