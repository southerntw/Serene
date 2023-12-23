import { body } from "express-validator";

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
  body("userId", "User ID must be numeric").isNumeric(),
];
