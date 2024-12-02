import api from './api';

const TOKEN_KEY = 'auth_token';

export const authService = {
  login: async (email, senha, tipoUsuario) => {
    try {
      const response = await api.post('/login', {
        email,
        senha,
        tipoUsuario
      });

      const { token } = response.data;
      
      console.log(response.data)

      // Armazenar o token e o usuário no localStorage
      localStorage.setItem(TOKEN_KEY, token);
      localStorage.setItem('email', JSON.stringify(response));
      
      return response;
    } catch (error) {
      console.error('Erro ao fazer login:', error);
      throw error;
    }
  },

  logout: () => {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem('email');
  },

  getToken: () => localStorage.getItem(TOKEN_KEY),

  isAuthenticated: () => !!localStorage.getItem(TOKEN_KEY),

  getUser: () => {
    const userStr = localStorage.getItem('email');
    return userStr ? JSON.parse(userStr) : null;
  }
};