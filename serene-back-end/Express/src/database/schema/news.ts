import {
  pgTable,
  serial,
  text,
  timestamp,
  uniqueIndex,
  varchar,
} from "drizzle-orm/pg-core";

export const news = pgTable("news", {
  id: serial("id").primaryKey(),
  writer: varchar("writer").notNull(),
  content: varchar("content").notNull(),
  category: varchar("category").notNull(),
});

export type News = typeof news.$inferSelect;
