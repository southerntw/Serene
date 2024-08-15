import { body, param } from "express-validator";

export const registerValidator = [
  body("name", "Name should be at least 4 characters").isLength({ min: 4 }),
  body("email", "Email should not be empty").not().isEmpty(),
  body("email", "Invalid email format").isEmail(),
  body("password", "Password should be at least 6 characters").isLength({
    min: 6,
  }),
];

export const loginValidator = [
  body("email", "Email should not be empty").not().isEmpty(),
  body("email", "Invalid email format").isEmail(),
  body("password", "Password should be at least 6 characters").isLength({
    min: 6,
  }),
];

export const chatValidator = [
  body("message", "Message should not be empty").not().isEmpty(),
  body("userId", "User ID should not be empty").not().isEmpty(),
  body("userId", "User ID must be a UUID").isUUID(),
];

export const getIdValidator = [
  param("id", "ID should not be empty").not().isEmpty(),
  param("id", "ID must be a number").isNumeric(),
];

export const userIdValidator = [
  param("id", "ID should not be empty").not().isEmpty(),
  param("id", "ID must be a UUID").isUUID(),
];

export const addMoodValidator = [
  body("userId", "User ID should not be empty").not().isEmpty(),
  body("type", "Mood type should not be empty").not().isEmpty(),
  body("description").optional(),
  body("audio").optional(),
];

export const postThreadValidator = [
  body("title", "Title should not be empty").not().isEmpty(),
  body("text", "Text should not be empty").not().isEmpty(),
  body("tag", "Tag should a string").isString().optional(),
  body("threadStarter", "threadStarter should not be empty").not().isEmpty(),
  body("threadStarter", "threadStarter must be string").isString(),
];

export const encourageValidator = [
  body("userId", "User ID should not be empty").not().isEmpty(),
  body("userId", "User ID must be a UUID").isUUID(),
];

export const editProfileValidator = [
  body("id", "ID should not be empty").not().isEmpty(),
  body("id", "ID must be a UUID").isUUID(),
  body("name", "Name should be at least 4 characters").isLength({ min: 4 }),
  body("email", "Email should not be empty").not().isEmpty(),
  body("email", "Invalid email format").normalizeEmail().isEmail(),
  body("password", "Password should be at least 6 characters").isLength({
    min: 6,
  }),
  body("avatar", "Avatar must be a string").isString().optional(),
  body("about", "About must be a string").isString().optional(),
  body("birthdate", "Birthdate must be a date").isISO8601().toDate().optional(),
  body("gender", "Gender should not be empty").isString().optional(),
];
