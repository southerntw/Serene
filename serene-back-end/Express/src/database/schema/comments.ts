import { pgTable, serial, varchar, integer, uuid } from "drizzle-orm/pg-core";
import { users } from "./users";
import { threads } from "./threads";

export const comments = pgTable("comments", {
  id: serial("id").primaryKey(),
  comment: varchar("comment").notNull(),
  userId: uuid("user_id").references(() => users.id),
  threadId: integer("thread_id").references(() => threads.id),
});

export type Comment = typeof comments.$inferSelect;
export type NewComment = typeof comments.$inferInsert;
