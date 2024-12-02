import api from './api';
import jwtDecode from 'jwt-decode';

const TOKEN_KEY = 'auth_token';

export const authService = {
  login: async (email, senha, tipoUsuario) => {
    try {
      const response = await api.post('/login', { email, senha, tipoUsuario });
  
      const { token } = response.data;
  
      // Armazena apenas o token e dados essenciais
      localStorage.setItem(TOKEN_KEY, token);
      localStorage.setItem('user', JSON.stringify({ email, tipoUsuario }));
  
      console.log('Login realizado com sucesso:', response.data);
      return response;
    } catch (error) {
      const errorMsg = error.response?.data?.message || 'Erro ao fazer login.';
      console.error(errorMsg);
      throw new Error(errorMsg);
    }
  },

  logout: () => {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem('user');
  },  

  getToken: () => localStorage.getItem(TOKEN_KEY),

  isAuthenticated: () => {
    const token = authService.getToken();
    if (!token) return false;
  
    try {
      const { exp } = jwtDecode(token); // Decodifica o token para verificar a expiração
      return exp * 1000 > Date.now();  // Verifica se ainda é válido
    } catch (error) {
      console.error('Erro ao validar token:', error);
      return false;
    }
  },

  getUser: () => {
    const userStr = localStorage.getItem('user');
    return userStr ? JSON.parse(userStr) : null;
  },  
};