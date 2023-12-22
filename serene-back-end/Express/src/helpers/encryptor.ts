import bcrypt from "bcrypt";

export const encrypt = async (str: string): Promise<string> => {
  const salt = await bcrypt.genSalt(10);
  const hashedString = await bcrypt.hash(str, salt);
  return hashedString;
};
