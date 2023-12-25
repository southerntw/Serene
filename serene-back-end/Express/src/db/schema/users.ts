import { pgTable, uuid, varchar, timestamp } from "drizzle-orm/pg-core";

export const users = pgTable(
  "users",
  {
    id: uuid("id").defaultRandom().primaryKey(),
    name: varchar("name").notNull(),
    email: varchar("email").notNull().unique(),
    password: varchar("password").notNull(),
    avatar: varchar("avatar"),
    about: varchar("about"),
    birthdate: timestamp("birth_date", { mode: "date" }),
    createdAt: timestamp("created_at").defaultNow().notNull(),
  },
  (table) => {
    // TODO: Add indexing.
    return {};
  },
);

export type User = typeof users.$inferSelect;
export type NewUser = typeof users.$inferInsert;
