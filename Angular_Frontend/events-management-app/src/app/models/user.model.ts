export interface User {
  userId: number;
  email: string;
  firstName: string;
  lastName: string;
  college: string;
  contact: string;
}

export interface AuthResponse {
  token: string | null;
  user: User | null;
  message: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  college: string;
  contact: string;
}
